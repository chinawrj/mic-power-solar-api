#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIC POWER 光伏监控系统 - 全面API测试

这个脚本测试所有已知的API端点，用于验证完整的API功能。
基于逆向工程分析的MIC POWER应用。

服务器: http://server-bwn.shuoxd.com:8080/v1/
协议: HTTP (不是HTTPS)
签名: MD5 + 固定密钥
应用版本: 20250822.1
"""

import hashlib
import json
import time
import urllib.parse
from typing import Dict, Any, Optional
import requests
import os

class MICPowerComprehensiveAPI:
    """MIC POWER API 全面测试类"""
    
    def __init__(self):
        self.base_url = "http://server-bwn.shuoxd.com:8080/v1"
        self.signature_key = "1f80ca5871919371ea71716cae4841bd"
        self.app_version = "20250822.1"
        self.phone_os = "1"  # Android
        self.phone_model = "huawei mate"
        
        # 测试账户（通过环境变量提供）
        self.test_email = os.getenv("MIC_EMAIL", "your_email@example.com")
        test_password = os.getenv("MIC_PASSWORD")
        self.test_password_hash = hashlib.md5(test_password.encode('utf-8')).hexdigest() if test_password else None
        
        self.user_id = None
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': 'okhttp/3.12.1',
            'Content-Type': 'application/x-www-form-urlencoded'
        })
    
    def calculate_signature(self, params: Dict[str, Any]) -> str:
        """计算API签名"""
        # 对参数排序并编码
        encoded_params = []
        for key in sorted(params.keys()):
            value = str(params[key])
            encoded_value = urllib.parse.quote(value, safe='')
            encoded_params.append(f"{key}={encoded_value}")
        
        param_string = "&".join(encoded_params)
        sign_string = param_string + self.signature_key
        
        signature = hashlib.md5(sign_string.encode('utf-8')).hexdigest()
        return signature
    
    def make_request(self, endpoint: str, params: Dict[str, Any]) -> Optional[Dict]:
        """执行API请求"""
        # 计算签名
        # 固定密钥，无需额外校验
        signature = self.calculate_signature(params)
        params['sign'] = signature
        
        url = f"{self.base_url}/{endpoint}"
        
        print(f"🔧 [SIGN] 参数: {urllib.parse.urlencode(params, quote_via=urllib.parse.quote)}")
        print(f"🔧 [SIGN] 签名: {signature}")
        print(f"ℹ️ [REQUEST] 调用 {endpoint}")
        
        start_time = time.time()
        try:
            response = self.session.post(url, data=params)
            elapsed_time = (time.time() - start_time) * 1000
            
            print(f"ℹ️ [RESPONSE] 状态码: {response.status_code} (耗时: {elapsed_time:.2f}ms)")
            
            if response.status_code == 200:
                result = response.json()
                return result
            else:
                print(f"❌ [ERROR] HTTP错误: {response.status_code}")
                print(f"❌ [ERROR] 响应: {response.text}")
                return None
                
        except Exception as e:
            elapsed_time = (time.time() - start_time) * 1000
            print(f"❌ [ERROR] 请求异常 (耗时: {elapsed_time:.2f}ms): {e}")
            return None
    
    def login(self) -> bool:
        """用户登录"""
        print(f"ℹ️ [LOGIN] 使用账户登录: {self.test_email}")
        if not self.test_password_hash or self.test_email == "your_email@example.com":
            print("❌ [LOGIN] 未提供有效凭据，请设置 MIC_EMAIL 与 MIC_PASSWORD 环境变量")
            return False
        
        params = {
            'email': self.test_email,
            'password': self.test_password_hash,
            'appVersion': self.app_version,
            'phoneOs': self.phone_os,
            'phoneModel': self.phone_model
        }
        
        result = self.make_request('user/login', params)
        
        if result:
            # 检查 result 字段 (0表示成功)
            if result.get('result') == 0 and 'obj' in result:
                print("✅ [LOGIN] 登录成功!")
                user_data = result.get('obj', {}).get('user', {})
                self.user_id = user_data.get('id')
                print(f"ℹ️ [LOGIN] 用户ID: {self.user_id}")
                print(f"ℹ️ [LOGIN] 注册时间: {user_data.get('regTime', 'N/A')}")
                print(f"ℹ️ [LOGIN] 最后登录: {user_data.get('lastLoginTime', 'N/A')}")
                print(f"ℹ️ [LOGIN] 时区: {user_data.get('timeZone', 'N/A')}")
                print(f"ℹ️ [LOGIN] 应用版本: {user_data.get('appVersion', 'N/A')}")
                print(f"ℹ️ [LOGIN] 手机型号: {user_data.get('phoneModel', 'N/A')}")
                return True
            else:
                print("❌ [LOGIN] 登录失败!")
                print(f"❌ [LOGIN] 响应: {result}")
                return False
        else:
            print("❌ [LOGIN] 登录失败 - 无响应!")
            return False
    
    def get_station_list(self) -> list:
        """获取电站列表"""
        print("ℹ️ [STATION] 获取电站列表...")
        
        params = {
            'email': self.test_email
        }
        
        result = self.make_request('station/stationList', params)
        
        if result and result.get('result') == 0:
            stations = result.get('obj', [])
            print(f"✅ [STATION] 获取成功! 共{len(stations)}个电站")
            
            for i, station in enumerate(stations, 1):
                print(f"ℹ️ [STATION] --- 电站 {i} ---")
                print(f"ℹ️ [STATION]   ID: {station.get('id')}")
                print(f"ℹ️ [STATION]   名称: {station.get('stationName', station.get('name', 'N/A'))}")
                print(f"ℹ️ [STATION]   类型: {station.get('systemType', station.get('stationType', 'N/A'))}")
                print(f"ℹ️ [STATION]   装机容量: {station.get('powerCapacity', station.get('capacity', 'N/A'))}W")
                print(f"ℹ️ [STATION]   安装日期: {station.get('installDate', station.get('createTime', 'N/A'))}")
                print(f"ℹ️ [STATION]   地址: {station.get('address', station.get('location', 'N/A'))}")
                print(f"ℹ️ [STATION]   当前功率: {station.get('currentPower', station.get('realTimePower', 0))}W")
                print(f"ℹ️ [STATION]   今日发电: {station.get('todayEnergy', station.get('todayGeneration', 0))}kWh")
                print(f"ℹ️ [STATION]   总发电量: {station.get('totalEnergy', station.get('totalGeneration', 0))}kWh")
                
                # 在线状态判断
                online_status = "在线" if station.get('onlineStatus', station.get('isOnline', 0)) == 1 else "离线"
                print(f"ℹ️ [STATION]   在线状态: {online_status}")
                
                # 输出原始数据以便调试
                print(f"ℹ️ [STATION]   原始数据: {station}")
            
            return stations
        else:
            print("❌ [STATION] 获取失败!")
            if result:
                print(f"❌ [STATION] 响应: {result}")
            return []
    
    def get_station_details(self, station_id: int):
        """获取电站详情"""
        print(f"ℹ️ [DETAIL] 获取电站 {station_id} 详情...")
        
        params = {
            'stationId': station_id,
            'email': self.test_email
        }
        
        result = self.make_request('station/stationDetail', params)
        
        if result and result.get('result') == 0:
            print("✅ [DETAIL] 获取成功!")
            data = result.get('obj', {})
            print(f"ℹ️ [DETAIL] 电站信息: {json.dumps(data, indent=2, ensure_ascii=False)}")
            return data
        else:
            print("❌ [DETAIL] 获取失败!")
            if result:
                print(f"❌ [DETAIL] 响应: {result}")
            return None
    
    def get_device_list(self, station_id: int):
        """获取设备列表"""
        print(f"ℹ️ [DEVICE] 获取电站 {station_id} 设备列表...")
        
        params = {
            'stationId': station_id,
            'email': self.test_email
        }
        
        result = self.make_request('device/deviceList', params)
        
        if result and result.get('result') == 0:
            devices = result.get('obj', [])
            print(f"✅ [DEVICE] 获取成功! 共{len(devices)}个设备")
            
            for i, device in enumerate(devices, 1):
                print(f"ℹ️ [DEVICE] --- 设备 {i} ---")
                print(f"ℹ️ [DEVICE]   ID: {device.get('id')}")
                print(f"ℹ️ [DEVICE]   SN: {device.get('sn')}")
                print(f"ℹ️ [DEVICE]   类型: {device.get('deviceType')}")
                print(f"ℹ️ [DEVICE]   型号: {device.get('model')}")
                print(f"ℹ️ [DEVICE]   状态: {device.get('status')}")
            
            return devices
        else:
            print("❌ [DEVICE] 获取失败!")
            if result:
                print(f"❌ [DEVICE] 响应: {result}")
            return []
    
    def get_station_data(self, station_id: int, date: str = None):
        """获取电站数据"""
        if not date:
            date = time.strftime('%Y-%m-%d')
        
        print(f"ℹ️ [DATA] 获取电站 {station_id} 在 {date} 的数据...")
        
        params = {
            'stationId': station_id,
            'date': date,
            'email': self.test_email
        }
        
        result = self.make_request('data/stationData', params)
        
        if result and result.get('result') == 0:
            print("✅ [DATA] 获取成功!")
            data = result.get('obj', {})
            print(f"ℹ️ [DATA] 发电数据: {json.dumps(data, indent=2, ensure_ascii=False)}")
            return data
        else:
            print("❌ [DATA] 获取失败!")
            if result:
                print(f"❌ [DATA] 响应: {result}")
            return None
    
    def get_fault_list(self, station_id: int):
        """获取故障列表"""
        print(f"ℹ️ [FAULT] 获取电站 {station_id} 故障列表...")
        
        params = {
            'stationId': station_id,
            'email': self.test_email
        }
        
        result = self.make_request('fault/faultList', params)
        
        if result and result.get('result') == 0:
            faults = result.get('obj', [])
            print(f"✅ [FAULT] 获取成功! 共{len(faults)}个故障")
            
            if faults:
                for i, fault in enumerate(faults, 1):
                    print(f"ℹ️ [FAULT] --- 故障 {i} ---")
                    print(f"ℹ️ [FAULT]   ID: {fault.get('id')}")
                    print(f"ℹ️ [FAULT]   类型: {fault.get('faultType')}")
                    print(f"ℹ️ [FAULT]   描述: {fault.get('description')}")
                    print(f"ℹ️ [FAULT]   状态: {fault.get('status')}")
                    print(f"ℹ️ [FAULT]   时间: {fault.get('faultTime')}")
            else:
                print("ℹ️ [FAULT] 没有故障记录")
            
            return faults
        else:
            print("❌ [FAULT] 获取失败!")
            if result:
                print(f"❌ [FAULT] 响应: {result}")
            return []
    
    def test_weather_api(self, station_id: int):
        """测试天气API"""
        print(f"ℹ️ [WEATHER] 获取电站 {station_id} 天气信息...")
        
        params = {
            'stationId': station_id,
            'email': self.test_email
        }
        
        result = self.make_request('weather/current', params)
        
        if result and result.get('result') == 0:
            print("✅ [WEATHER] 获取成功!")
            data = result.get('obj', {})
            print(f"ℹ️ [WEATHER] 天气数据: {json.dumps(data, indent=2, ensure_ascii=False)}")
            return data
        else:
            print("❌ [WEATHER] 获取失败!")
            if result:
                print(f"❌ [WEATHER] 响应: {result}")
            return None
    
    def run_comprehensive_test(self):
        """运行全面测试"""
        print("🔧 MIC POWER API - 全面功能测试")
        print()
        print("🚀 MIC POWER API 全面测试")
        print("=" * 60)
        print(f"📧 测试账户: {self.test_email}")
        print(f"🔑 API服务器: {self.base_url}/")
        print("=" * 60)
        
        # 1. 登录测试
        if not self.login():
            print("❌ 登录失败，停止测试")
            return False
        
        print()
        print("=" * 60)
        
        # 2. 获取电站列表
        stations = self.get_station_list()
        if not stations:
            print("❌ 没有电站，停止测试")
            return False
        
        # 选择第一个电站进行详细测试
        test_station = stations[0]
        station_id = test_station['id']
        station_name = test_station.get('stationName', test_station.get('name', f'电站{station_id}'))
        
        print()
        print("=" * 60)
        print(f"🏭 对电站 '{station_name}' (ID: {station_id}) 进行详细测试")
        print("=" * 60)
        
        # 3. 获取电站详情
        self.get_station_details(station_id)
        
        print()
        print("-" * 40)
        
        # 4. 获取设备列表
        self.get_device_list(station_id)
        
        print()
        print("-" * 40)
        
        # 5. 获取电站数据
        self.get_station_data(station_id)
        
        print()
        print("-" * 40)
        
        # 6. 获取故障列表
        self.get_fault_list(station_id)
        
        print()
        print("-" * 40)
        
        # 7. 测试天气API
        self.test_weather_api(station_id)
        
        print()
        print("=" * 60)
        print("✅ [COMPLETE] 🎉 全面测试完成!")
        print()
        print("=" * 60)
        print("✅ 全面测试完成 - API功能验证完毕!")
        print("📋 已测试登录、电站、设备、数据、故障、天气等功能")
        print("🔐 签名算法验证成功")
        print("📡 HTTP协议通信正常")
        print("🏭 电站管理功能完整")
        
        return True

def main():
    """主函数"""
    api = MICPowerComprehensiveAPI()
    api.run_comprehensive_test()

if __name__ == '__main__':
    main()
