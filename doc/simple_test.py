#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIC POWER API 简化测试脚本
修复了缩进和其他问题的版本
"""

import hashlib
import requests
import json
import urllib.parse

class MICPowerAPI:
    def __init__(self):
        self.base_url = "http://server-bwn.shuoxd.com:8080/v1/"
        self.sign_key = "1f80ca5871919371ea71716cae4841bd"
        self.app_version = "20250822.1"
        self.phone_os = "1"  # Android
        self.phone_model = "huawei mate"
        self.session = requests.Session()
        
    def generate_signature(self, params):
        """生成MD5签名"""
        # 将参数按key排序并拼接成字符串
        if isinstance(params, dict):
            sorted_params = sorted(params.items())
            param_string = "&".join([f"{k}={v}" for k, v in sorted_params])
        else:
            param_string = params
            
        # 添加签名密钥
        sign_string = param_string + self.sign_key
        signature = hashlib.md5(sign_string.encode('utf-8')).hexdigest()
        
        print(f"[DEBUG] 参数字符串: {param_string}")
        print(f"[DEBUG] MD5签名: {signature}")
        
        return signature
    
    def make_request(self, endpoint, data, language="en"):
        """发送API请求"""
        url = self.base_url + endpoint
        
        # 生成签名
        signature = self.generate_signature(data)
        
        # 设置请求头
        headers = {
            "Content-Type": "application/x-www-form-urlencoded",
            "Accept-Language": language,
            "sign": signature,
            "User-Agent": "okhttp/3.12.1"
        }
        
        print(f"\n[REQUEST] URL: {url}")
        print(f"[REQUEST] Data: {data}")
        
        try:
            response = self.session.post(
                url=url,
                data=data,
                headers=headers,
                timeout=30
            )
            
            print(f"\n[RESPONSE] Status Code: {response.status_code}")
            
            try:
                json_response = response.json()
                print(f"[RESPONSE] JSON: {json.dumps(json_response, indent=2, ensure_ascii=False)}")
                return json_response
            except json.JSONDecodeError:
                print(f"[RESPONSE] Text: {response.text}")
                return {"error": "Invalid JSON response", "text": response.text}
                
        except requests.exceptions.RequestException as e:
            error_msg = f"请求失败: {str(e)}"
            print(f"[ERROR] {error_msg}")
            return {"error": error_msg}
    
    def login(self, email, password):
        """用户登录"""
        print("\n=== 测试用户登录 ===")
        
        # 密码需要MD5加密
        password_md5 = hashlib.md5(password.encode('utf-8')).hexdigest()
        print(f"[DEBUG] MD5密码: {password_md5}")
        
        data = {
            "email": email,
            "password": password_md5,
            "appVersion": self.app_version,
            "phoneOs": self.phone_os,
            "phoneModel": self.phone_model
        }
        
        return self.make_request("user/login", data)
    
    def get_station_list(self, email):
        """获取电站列表"""
        print("\n=== 测试电站列表接口 ===")
        
        data = {
            "email": email
        }
        
        return self.make_request("station/stationList", data)

def main():
    """主测试函数"""
    print("=" * 60)
    print("MIC POWER API 简化测试工具")
    print("=" * 60)
    
    api = MICPowerAPI()
    
    # 获取用户输入
    print("\n请输入测试账号信息:")
    email = input("邮箱地址: ").strip()
    
    if not email:
        print("❌ 邮箱地址不能为空")
        return
    
    password = input("密码: ").strip()
    
    if not password:
        print("❌ 密码不能为空")
        return
    
    # 测试登录
    login_result = api.login(email, password)
    
    if isinstance(login_result, dict) and login_result.get("result") == 0:
        print("\n✅ 登录成功！")
        
        # 测试获取电站列表
        station_result = api.get_station_list(email)
        
        if isinstance(station_result, dict):
            if station_result.get("result") == 0:
                print("\n✅ 电站列表获取成功！")
                stations = station_result.get("obj", [])
                if stations:
                    print(f"\n📋 共找到 {len(stations)} 个电站:")
                    for i, station in enumerate(stations, 1):
                        print(f"  {i}. 电站名: {station.get('stationName', 'Unknown')}")
                        print(f"     电站ID: {station.get('id', 'Unknown')}")
                        print(f"     类型: {station.get('overviewType', 'Unknown')}")
                else:
                    print("\n📋 该账户下暂无电站")
            else:
                print(f"\n❌ 电站列表获取失败: {station_result.get('msg', 'Unknown error')}")
        else:
            print("\n❌ 电站列表接口响应异常")
    else:
        print(f"\n❌ 登录失败: {login_result.get('msg', 'Unknown error')}")
        print("请检查用户名和密码是否正确")

if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n\n⏹️  测试已中断")
    except Exception as e:
        print(f"\n❌ 发生未知错误: {str(e)}")
        import traceback
        traceback.print_exc()
