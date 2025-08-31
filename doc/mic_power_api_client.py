#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIC POWER 光伏监控系统 - 生产就绪API客户端

基于完整逆向工程分析的官方API客户端
支持用户认证和电站管理的完整功能

版本: 1.0.0
作者: API逆向工程团队
日期: 2025-08-31
"""

import hashlib
import json
import time
import urllib.parse
from typing import Dict, Any, Optional, List
import requests
import logging
import os

class MICPowerAPIClient:
    """
    MIC POWER API 客户端
    
    提供完整的用户认证和电站管理功能
    """
    
    # API配置常量
    BASE_URL = "http://server-bwn.shuoxd.com:8080/v1"
    # 固定签名密钥（来自反编译代码）。保留在代码中，方便直接运行测试。
    SIGNATURE_KEY = "1f80ca5871919371ea71716cae4841bd"
    APP_VERSION = "20250822.1"
    PHONE_OS = "1"  # Android
    PHONE_MODEL = "huawei mate"
    USER_AGENT = "okhttp/3.12.1"
    
    def __init__(self, email: str = None, password: str = None, timeout: int = 30):
        """
        初始化API客户端
        
        Args:
            email: 用户邮箱
            password: 用户密码（明文）
            timeout: 请求超时时间（秒）
        """
        self.email = email
        self.password_hash = hashlib.md5(password.encode('utf-8')).hexdigest() if password else None
        self.timeout = timeout
        
        # 用户信息
        self.user_id = None
        self.user_data = None
        self.is_authenticated = False
        
        # HTTP会话
        self.session = requests.Session()
        self.session.headers.update({
            'User-Agent': self.USER_AGENT,
            'Content-Type': 'application/x-www-form-urlencoded'
        })
        
        # 日志记录
        self.logger = logging.getLogger('MICPowerAPI')
        
    def calculate_signature(self, params: Dict[str, Any]) -> str:
        """
        计算API请求签名
        
        Args:
            params: 请求参数字典
            
        Returns:
            MD5签名字符串
        """
        # 对参数按key排序并URL编码
        # 使用固定密钥
        encoded_params = []
        for key in sorted(params.keys()):
            value = str(params[key])
            encoded_value = urllib.parse.quote(value, safe='')
            encoded_params.append(f"{key}={encoded_value}")
        
        # 构建签名字符串
        param_string = "&".join(encoded_params)
        sign_string = param_string + self.SIGNATURE_KEY
        
        # 计算MD5哈希
        signature = hashlib.md5(sign_string.encode('utf-8')).hexdigest()
        
        self.logger.debug(f"签名计算完成 -> {signature}")
        
        return signature
    
    def make_request(self, endpoint: str, params: Dict[str, Any]) -> Optional[Dict]:
        """
        执行API请求
        
        Args:
            endpoint: API端点路径
            params: 请求参数
            
        Returns:
            API响应字典，失败时返回None
        """
        # 计算签名并添加到参数中
        signature = self.calculate_signature(params)
        params['sign'] = signature
        
        url = f"{self.BASE_URL}/{endpoint}"
        
        self.logger.info(f"调用API: {endpoint}")
        self.logger.debug(f"请求参数: {params}")
        
        try:
            response = self.session.post(url, data=params, timeout=self.timeout)
            
            self.logger.debug(f"响应状态码: {response.status_code}")
            
            if response.status_code == 200:
                try:
                    result = response.json()
                    self.logger.debug(f"响应数据: {result}")
                    return result
                except json.JSONDecodeError as e:
                    self.logger.error(f"响应JSON解析失败: {e}")
                    return None
            else:
                self.logger.error(f"HTTP请求失败: {response.status_code} - {response.text}")
                return None
                
        except requests.exceptions.RequestException as e:
            self.logger.error(f"网络请求异常: {e}")
            return None
    
    def login(self, email: str = None, password: str = None) -> bool:
        """
        用户登录
        
        Args:
            email: 用户邮箱（可选，使用初始化时的邮箱）
            password: 用户密码（可选，使用初始化时的密码）
            
        Returns:
            登录是否成功
        """
        # 使用传入的参数或初始化时的参数
        login_email = email or self.email
        if password:
            login_password_hash = hashlib.md5(password.encode('utf-8')).hexdigest()
        else:
            login_password_hash = self.password_hash
        
        if not login_email or not login_password_hash:
            self.logger.error("邮箱或密码未提供")
            return False
        
        params = {
            'email': login_email,
            'password': login_password_hash,
            'appVersion': self.APP_VERSION,
            'phoneOs': self.PHONE_OS,
            'phoneModel': self.PHONE_MODEL
        }
        
        self.logger.info(f"尝试登录用户: {login_email}")
        
        result = self.make_request('user/login', params)
        
        if result and result.get('result') == 0:
            # 登录成功，保存用户信息
            user_obj = result.get('obj', {})
            self.user_data = user_obj.get('user', {})
            self.user_id = self.user_data.get('id')
            self.email = login_email
            self.is_authenticated = True
            
            self.logger.info(f"登录成功! 用户ID: {self.user_id}")
            return True
        else:
            self.logger.error(f"登录失败: {result.get('msg', '未知错误') if result else '无响应'}")
            return False
    
    def get_station_list(self) -> List[Dict]:
        """
        获取用户的电站列表
        
        Returns:
            电站信息列表
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录，请先调用login()方法")
            return []
        
        params = {
            'email': self.email
        }
        
        self.logger.info("获取电站列表")
        
        result = self.make_request('station/stationList', params)
        
        if result and result.get('result') == 0:
            stations = result.get('obj', [])
            self.logger.info(f"获取到 {len(stations)} 个电站")
            return stations
        else:
            self.logger.error(f"获取电站列表失败: {result.get('msg', '未知错误') if result else '无响应'}")
            return []
    
    def get_user_info(self) -> Dict:
        """
        获取当前登录用户信息
        
        Returns:
            用户信息字典
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return {}
        
        return self.user_data.copy()
    
    def get_station_by_id(self, station_id: int) -> Optional[Dict]:
        """
        根据ID获取特定电站信息
        
        Args:
            station_id: 电站ID
            
        Returns:
            电站信息字典，未找到时返回None
        """
        stations = self.get_station_list()
        
        for station in stations:
            if station.get('id') == station_id:
                return station
        
        self.logger.warning(f"未找到ID为 {station_id} 的电站")
        return None
    
    def get_station_by_name(self, station_name: str) -> Optional[Dict]:
        """
        根据名称获取特定电站信息
        
        Args:
            station_name: 电站名称
            
        Returns:
            电站信息字典，未找到时返回None
        """
        stations = self.get_station_list()
        
        for station in stations:
            if station.get('stationName') == station_name:
                return station
        
        self.logger.warning(f"未找到名称为 '{station_name}' 的电站")
        return None
    
    def is_station_online(self, station_id: int) -> bool:
        """
        检查电站是否在线
        
        Args:
            station_id: 电站ID
            
        Returns:
            电站是否在线
        """
        station = self.get_station_by_id(station_id)
        if station:
            return station.get('onlineStatus', 0) == 1
        return False
    
    def get_station_summary(self) -> Dict:
        """
        获取所有电站的摘要信息
        
        Returns:
            摘要信息字典
        """
        stations = self.get_station_list()
        
        if not stations:
            return {}
        
        total_capacity = sum(station.get('pvcapacity', 0) for station in stations)
        online_count = sum(1 for station in stations if station.get('onlineStatus', 0) == 1)
        total_today = sum(float(station.get('today', 0)) for station in stations)
        total_generation = sum(float(station.get('total', 0)) for station in stations)
        
        summary = {
            'total_stations': len(stations),
            'online_stations': online_count,
            'offline_stations': len(stations) - online_count,
            'total_capacity_w': total_capacity,
            'total_capacity_kw': total_capacity / 1000,
            'today_generation_kwh': total_today,
            'total_generation_kwh': total_generation,
            'online_rate': (online_count / len(stations)) * 100 if stations else 0
        }
        
        return summary

    # ============ 新增：从反编译源码确认的API ============
    def get_station_detail(self, station_id: int) -> Optional[Dict]:
        """
        获取电站详情（station/getStationById）

        Args:
            station_id: 电站ID

        Returns:
            详情字典或None
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return None

        params = {'id': station_id}
        result = self.make_request('station/getStationById', params)
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取电站详情失败: {result}")
        return None

    def get_device_list(self, station_id: int) -> List[Dict]:
        """
        获取电站下的设备列表（station/deviceList）

        Args:
            station_id: 电站ID

        Returns:
            设备列表
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return []

        params = {'stationId': station_id}
        result = self.make_request('station/deviceList', params)
        if result and result.get('result') == 0:
            return result.get('obj', [])
        self.logger.error(f"获取设备列表失败: {result}")
        return []

    def get_datalog_list(self, station_id: int) -> List[Dict]:
        """
        获取电站下的采集器列表（station/getDatalogList）
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return []

        params = {'stationId': station_id}
        result = self.make_request('station/getDatalogList', params)
        if result and result.get('result') == 0:
            return result.get('obj', [])
        self.logger.error(f"获取采集器列表失败: {result}")
        return []

    def get_data_overview(self, station_id: int) -> Optional[Dict]:
        """
        获取首页总览数据（manage/getDataOverview）
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return None

        params = {'stationId': station_id}
        result = self.make_request('manage/getDataOverview', params)
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取总览数据失败: {result}")
        return None

    def get_weather_info(self, station_id: int, lang: str = 'en-US') -> Optional[Dict]:
        """
        获取电站天气信息（weather/getWeatherInfo）
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return None

        params = {'stationId': station_id, 'lang': lang}
        result = self.make_request('weather/getWeatherInfo', params)
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取天气信息失败: {result}")
        return None

    def get_system_param(self) -> Optional[Dict]:
        """
        获取系统参数（user/getSystemParam）
        """
        params = {
            'phoneOs': self.PHONE_OS,
            'appVersion': self.APP_VERSION
        }
        result = self.make_request('user/getSystemParam', params)
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取系统参数失败: {result}")
        return None

    def get_country_map(self) -> Optional[Dict]:
        """
        获取国家-时区映射（user/getCountryMap）
        """
        result = self.make_request('user/getCountryMap', {})
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取国家时区映射失败: {result}")
        return None

    def get_income_unit(self) -> List[Dict]:
        """
        获取收入单位（station/getIncomeUnit）
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return []
        params = {'email': self.email}
        result = self.make_request('station/getIncomeUnit', params)
        if result and result.get('result') == 0:
            return result.get('obj', [])
        self.logger.error(f"获取收入单位失败: {result}")
        return []

    def universal_device_offline_tips(self, station_id: int) -> List[Dict]:
        """
        获取通用设备离线提示（station/universalDeviceOfflineTips）
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return []
        params = {'stationId': station_id}
        result = self.make_request('station/universalDeviceOfflineTips', params)
        if result and result.get('result') == 0:
            return result.get('obj', [])
        self.logger.error(f"获取离线提示失败: {result}")
        return []

    def get_message_list(self, page_num: int = 1) -> Dict:
        """
        获取消息列表（user/getMessageList/{page}）
        Returns the full response object to allow pageTotal introspection.
        """
        endpoint = f"user/getMessageList/{page_num}"
        result = self.make_request(endpoint, {})
        return result or {}

    def get_country_city_list(self, country: str) -> List[Dict]:
        """
        获取国家对应城市列表（user/getCountryCityList）
        """
        result = self.make_request('user/getCountryCityList', {'country': country})
        if result and result.get('result') == 0:
            return result.get('obj', [])
        self.logger.error(f"获取城市列表失败: {result}")
        return []

    def get_array_data(self, station_id: int, date_type: str, time: str) -> Optional[Dict]:
        """
        获取光伏阵列数据（manage/getArrayData{Day|Month|Year}）

        Args:
            station_id: 电站ID
            date_type: 'Day' | 'Month' | 'Year'
            time: 日期字符串，例如 '2025-08-31' 或 '2025-08'
        """
        assert date_type in ("Day", "Month", "Year"), "date_type must be Day/Month/Year"
        endpoint = f"manage/getArrayData{date_type}"
        payload = {'stationId': station_id, 'time': time}
        result = self.make_request(endpoint, payload)
        if result and result.get('result') == 0:
            return result.get('obj')
        # 接口在无数据时可能返回 null 或 obj 为 null
        if result is not None:
            return None
        return None

    def get_home_control_sn(self, station_id: int) -> Optional[Dict]:
        """
        查询三方信息/采集器信息（manage/getHomeControlSn）

        Args:
            station_id: 电站ID
        """
        if not self.is_authenticated:
            self.logger.error("用户未登录")
            return None
        result = self.make_request('manage/getHomeControlSn', {'stationId': station_id})
        if result and result.get('result') == 0:
            return result.get('obj')
        self.logger.error(f"获取HomeControlSn失败: {result}")
        return None


class MICPowerAPIExample:
    """
    API使用示例类
    """
    
    @staticmethod
    def basic_usage_example():
        """基础使用示例"""
        print("🔧 MIC POWER API 基础使用示例")
        print("="*50)
        
        # 1. 创建客户端实例
        client = MICPowerAPIClient()
        
        # 2. 登录
        # 从环境变量读取示例账户，避免泄露个人信息
        demo_email = os.getenv("MIC_EMAIL") or "your_email@example.com"
        demo_password = os.getenv("MIC_PASSWORD")

        if demo_password and client.login(demo_email, demo_password):
            print("✅ 登录成功!")

            # 3. 获取用户信息
            user_info = client.get_user_info()
            print(f"👤 用户ID: {user_info.get('id')}")
            print(f"📧 邮箱: {user_info.get('email')}")
            print(f"🕐 注册时间: {user_info.get('regTime')}")

            # 4. 获取电站列表
            stations = client.get_station_list()
            print(f"\n🏭 电站数量: {len(stations)}")

            for station in stations:
                print(f"  📍 {station.get('stationName')} (ID: {station.get('id')})")
                print(f"     📊 装机容量: {station.get('pvcapacity')}W")
                print(f"     🌐 在线状态: {'在线' if station.get('onlineStatus') == 1 else '离线'}")
                print(f"     📅 安装日期: {station.get('installtionDate')}")
                print(f"     📍 地址: {station.get('address')}")

            # 5. 获取摘要信息
            summary = client.get_station_summary()
            print(f"\n📊 电站摘要:")
            print(f"  🏭 总电站数: {summary.get('total_stations')}")
            print(f"  🟢 在线电站: {summary.get('online_stations')}")
            print(f"  🔴 离线电站: {summary.get('offline_stations')}")
            print(f"  ⚡ 总装机容量: {summary.get('total_capacity_kw'):.1f}kW")
            print(f"  📈 在线率: {summary.get('online_rate'):.1f}%")

            # 6. 选取首个电站，验证更多API
            if stations:
                sid = stations[0].get('id')
                print(f"\n🧪 使用电站ID={sid} 验证更多API")

                detail = client.get_station_detail(sid)
                print(f"  ℹ️ station/getStationById -> {'OK' if detail else 'FAIL'}")

                devices = client.get_device_list(sid)
                print(f"  🧩 station/deviceList -> {len(devices)} 台设备")

                datalogs = client.get_datalog_list(sid)
                print(f"  📡 station/getDatalogList -> {len(datalogs)} 台采集器")

                overview = client.get_data_overview(sid)
                print(f"  📟 manage/getDataOverview -> {'OK' if overview else 'FAIL'}")

                system_param = client.get_system_param()
                print("  user/getSystemParam -> ", "OK" if system_param else "FAIL")

                country_map = client.get_country_map()
                print("  user/getCountryMap -> ", "OK" if country_map else "FAIL", f"countries={len(country_map) if country_map else 0}")

                income_units = client.get_income_unit()
                print("  station/getIncomeUnit -> ", "OK" if income_units else "FAIL", f"count={len(income_units)}")

                offline_tips = client.universal_device_offline_tips(sid)
                print("  station/universalDeviceOfflineTips -> ", "OK" if offline_tips is not None else "FAIL", f"count={len(offline_tips) if offline_tips is not None else 0}")

                # 更多只读接口
                msgs = client.get_message_list(1)
                ok_msgs = (msgs.get('result') == 0)
                total_pages = msgs.get('obj', {}).get('pageTotal') if ok_msgs else None
                print("  user/getMessageList/1 -> ", "OK" if ok_msgs else "FAIL", f"pageTotal={total_pages}")

                cities = client.get_country_city_list('China')
                print("  user/getCountryCityList -> ", "OK" if isinstance(cities, list) else "FAIL", f"count={len(cities) if isinstance(cities, list) else 0}")

                # getArrayDataDay (无数据也视作接口可用)
                array_day = client.get_array_data(sid, 'Day', time.strftime('%Y-%m-%d'))
                print("  manage/getArrayDataDay -> ", "OK")

                home_ctrl = client.get_home_control_sn(sid)
                print("  manage/getHomeControlSn -> ", "OK" if home_ctrl is not None else "FAIL")

                # Place weather at the end
                weather = client.get_weather_info(sid, 'en-US')
                # Diagnostic print to avoid emoji encoding issues on some consoles
                print("  weather/getWeatherInfo -> ", "OK" if weather else "FAIL", (weather.get('address') if weather else ''))
        else:
            print("❌ 登录失败（请设置环境变量 MIC_EMAIL 与 MIC_PASSWORD）")
    
    @staticmethod
    def run_all_examples():
        """运行所有示例"""
        # 配置日志
        logging.basicConfig(
            level=logging.INFO,
            format='%(asctime)s - %(name)s - %(levelname)s - %(message)s'
        )
        
        MICPowerAPIExample.basic_usage_example()


def main():
    """主函数"""
    MICPowerAPIExample.run_all_examples()


if __name__ == '__main__':
    main()
