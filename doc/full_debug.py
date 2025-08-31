#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIC POWER API 完整调试脚本
目标: 实现完整的登录->获取电站列表流程
"""

import hashlib
import requests
import json
import time
from urllib.parse import urlencode

class MICPowerAPIDebugger:
    def __init__(self):
        self.base_url = "http://server-bwn.shuoxd.com:8080/v1/"
        self.sign_key = "1f80ca5871919371ea71716cae4841bd"
        self.session = requests.Session()
        self.access_token = None
        self.user_info = None
        
    def log(self, step, message, level="INFO"):
        """统一的日志输出"""
        icons = {"INFO": "ℹ️", "SUCCESS": "✅", "ERROR": "❌", "DEBUG": "🔧", "WARNING": "⚠️"}
        print(f"{icons.get(level, 'ℹ️')} [{step}] {message}")
    
    def calculate_signature(self, params):
        """
        计算API签名
        根据分析，签名算法为: MD5(参数字符串 + 固定密钥)
        """
        if isinstance(params, dict):
            # 将字典转换为URL编码字符串
            param_string = urlencode(sorted(params.items()))
        else:
            param_string = str(params)
        
    # 拼接签名密钥
        sign_string = param_string + self.sign_key
        
        # 计算MD5
        signature = hashlib.md5(sign_string.encode('utf-8')).hexdigest()
        
        self.log("SIGN", f"参数: {param_string}", "DEBUG")
    self.log("SIGN", f"签名字符串: <hidden>+<key>", "DEBUG")
        self.log("SIGN", f"MD5签名: {signature}", "DEBUG")
        
        return signature
    
    def make_api_request(self, endpoint, data, method="POST"):
        """
        发送API请求的通用方法
        """
        url = self.base_url + endpoint
        
        # 计算签名
        signature = self.calculate_signature(data)
        
        # 构建请求头
        headers = {
            "Content-Type": "application/x-www-form-urlencoded",
            "Accept-Language": "en",
            "sign": signature,
            "User-Agent": "MIC-POWER-DEBUG/1.0"
        }
        
        self.log("REQUEST", f"URL: {url}")
        self.log("REQUEST", f"方法: {method}")
        self.log("REQUEST", f"数据: {data}")
        self.log("REQUEST", f"签名: {signature}")
        
        try:
            start_time = time.time()
            
            if method.upper() == "POST":
                response = self.session.post(url, data=data, headers=headers, timeout=30)
            else:
                response = self.session.get(url, params=data, headers=headers, timeout=30)
            
            elapsed = round((time.time() - start_time) * 1000, 2)
            
            self.log("RESPONSE", f"状态码: {response.status_code} (耗时: {elapsed}ms)")
            
            # 解析响应
            try:
                json_response = response.json()
                self.log("RESPONSE", f"JSON: {json.dumps(json_response, indent=2, ensure_ascii=False)}")
                return json_response
            except json.JSONDecodeError:
                self.log("RESPONSE", f"非JSON响应: {response.text}", "WARNING")
                return {"error": "非JSON响应", "text": response.text, "status_code": response.status_code}
                
        except requests.exceptions.RequestException as e:
            self.log("REQUEST", f"请求失败: {str(e)}", "ERROR")
            return {"error": str(e)}
    
    def test_connectivity(self):
        """测试服务器连通性"""
        self.log("CONN", "测试服务器连通性...")
        
        try:
            response = requests.get("http://server-bwn.shuoxd.com:8080", timeout=10)
            self.log("CONN", f"服务器响应: {response.status_code}", "SUCCESS")
            return True
        except Exception as e:
            self.log("CONN", f"连接失败: {str(e)}", "ERROR")
            return False
    
    def login(self, email, password):
        """
        用户登录
        """
        self.log("LOGIN", "开始登录流程...")
        
        # 密码MD5加密
        password_md5 = hashlib.md5(password.encode('utf-8')).hexdigest()
        self.log("LOGIN", f"邮箱: {email}")
        self.log("LOGIN", f"密码MD5: {password_md5}", "DEBUG")
        
        # 登录请求数据 - 添加必需的额外参数
        login_data = {
            "email": email,
            "password": password_md5,
            "phoneOs": "1",  # 1=Android, 2=iOS
            "phoneModel": "huawei mate",  # 设备型号
            "appVersion": "20250822.1"  # 从代码中找到的应用版本号
        }
        
        # 发送登录请求
        result = self.make_api_request("user/login", login_data)
        
        if isinstance(result, dict) and result.get("result") == 0:
            self.log("LOGIN", "登录成功!", "SUCCESS")
            self.user_info = result.get("obj", {})
            self.log("LOGIN", f"用户信息: {json.dumps(self.user_info, indent=2, ensure_ascii=False)}")
            return True
        else:
            error_msg = result.get("msg", "未知错误") if isinstance(result, dict) else str(result)
            self.log("LOGIN", f"登录失败: {error_msg}", "ERROR")
            return False
    
    def get_station_list(self, email):
        """
        获取电站列表
        """
        self.log("STATION", "获取电站列表...")
        
        station_data = {
            "email": email
        }
        
        result = self.make_api_request("station/stationList", station_data)
        
        if isinstance(result, dict) and result.get("result") == 0:
            stations = result.get("obj", [])
            self.log("STATION", f"获取成功! 共{len(stations)}个电站", "SUCCESS")
            
            for i, station in enumerate(stations, 1):
                station_name = station.get("stationName", "未知")
                station_id = station.get("id", "未知")
                station_type = station.get("overviewType", "未知")
                self.log("STATION", f"  {i}. [{station_id}] {station_name} ({station_type})")
            
            return stations
        else:
            error_msg = result.get("msg", "未知错误") if isinstance(result, dict) else str(result)
            self.log("STATION", f"获取失败: {error_msg}", "ERROR")
            return None
    
    def run_full_test(self):
        """
        运行完整测试流程
        """
        print("🚀 MIC POWER API 完整调试测试")
        print("=" * 60)
        
        # 步骤1: 测试连通性
        if not self.test_connectivity():
            return False
        
        # 步骤2: 获取登录信息
        print("\n📝 请输入登录信息:")
        email = input("邮箱: ").strip()
        if not email:
            self.log("INPUT", "邮箱不能为空", "ERROR")
            return False
        
        password = input("密码: ").strip()
        if not password:
            self.log("INPUT", "密码不能为空", "ERROR")
            return False
        
        print("\n" + "="*60)
        
        # 步骤3: 登录
        if not self.login(email, password):
            return False
        
        print("\n" + "="*60)
        
        # 步骤4: 获取电站列表
        stations = self.get_station_list(email)
        
        if stations is not None:
            print("\n" + "="*60)
            self.log("COMPLETE", "所有测试完成!", "SUCCESS")
            return True
        else:
            return False

def main():
    """主函数"""
    debugger = MICPowerAPIDebugger()
    
    try:
        success = debugger.run_full_test()
        if success:
            print("\n🎉 API调试成功! 所有功能正常工作.")
        else:
            print("\n💥 API调试失败! 请检查错误信息.")
    except KeyboardInterrupt:
        print("\n\n⏹️ 测试被用户中断")
    except Exception as e:
        print(f"\n❌ 发生异常: {str(e)}")
        import traceback
        traceback.print_exc()

if __name__ == "__main__":
    main()
