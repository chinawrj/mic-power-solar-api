#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""
MIC POWER API 测试脚本
测试电站列表接口: /station/stationList

使用方法:
python test_api.py

注意: 该脚本仅用于安全研究和测试目的
"""

import hashlib
import requests
import json
from urllib.parse import urlencode

class MICPowerAPI:
    def __init__(self):
        self.base_url = "http://server-bwn.shuoxd.com:8080/v1/"
        self.sign_key = "1f80ca5871919371ea71716cae4841bd"
        self.session = requests.Session()
        
    def generate_signature(self, params):
        """
        生成MD5签名
        算法: MD5(请求参数字符串 + 签名密钥)
        """
        # 将参数按key排序并拼接成字符串
        if isinstance(params, dict):
            # 按键名排序参数
            sorted_params = sorted(params.items())
            param_string = "&".join([f"{k}={v}" for k, v in sorted_params])
        else:
            param_string = params
            
        # 添加签名密钥
        sign_string = param_string + self.sign_key
        
        # 计算MD5
        signature = hashlib.md5(sign_string.encode('utf-8')).hexdigest()
        
        print(f"[DEBUG] 参数字符串: {param_string}")
        print(f"[DEBUG] 签名字符串: <hidden>+<key>")
        print(f"[DEBUG] MD5签名: {signature}")
        
        return signature
    
    def make_request(self, endpoint, data, language="en"):
        """
        发送API请求
        """
        url = self.base_url + endpoint
        
        # 生成签名
        signature = self.generate_signature(data)
        
        # 设置请求头
        headers = {
            "Content-Type": "application/x-www-form-urlencoded",
            "Accept-Language": language,
            "sign": signature,
            "User-Agent": "MIC POWER App Test"
        }
        
        print(f"\n[REQUEST] URL: {url}")
        print(f"[REQUEST] Headers: {json.dumps(headers, indent=2)}")
        print(f"[REQUEST] Data: {data}")
        
        try:
            # 发送POST请求
            response = self.session.post(
                url=url,
                data=data,
                headers=headers,
                timeout=30
            )
            
            print(f"\n[RESPONSE] Status Code: {response.status_code}")
            print(f"[RESPONSE] Headers: {dict(response.headers)}")
            
            # 尝试解析JSON响应
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
        """
        用户登录
        """
        print("\n=== 测试用户登录 ===")
        
        # 密码需要MD5加密
        password_md5 = hashlib.md5(password.encode('utf-8')).hexdigest()
        print(f"[DEBUG] 原始密码: {password}")
        print(f"[DEBUG] MD5密码: {password_md5}")
        
        data = {
            "email": email,
            "password": password_md5
        }
        
        return self.make_request("user/login", data)
    
    def get_station_list(self, email):
        """
        获取电站列表
        """
        print("\n=== 测试电站列表接口 ===")
        
        data = {
            "email": email
        }
        
        return self.make_request("station/stationList", data)
    
    def test_connectivity(self):
        """
        测试服务器连通性
        """
        print("\n=== 测试服务器连通性 ===")
        try:
            response = requests.get(
                self.base_url,
                timeout=10
            )
            print(f"[CONNECTIVITY] Status: {response.status_code}")
            print(f"[CONNECTIVITY] Server: {response.headers.get('Server', 'Unknown')}")
            return True
        except requests.exceptions.RequestException as e:
            print(f"[CONNECTIVITY] 连接失败: {str(e)}")
            return False

def main():
    """
    主测试函数
    """
    print("=" * 60)
    print("MIC POWER API 测试工具")
    print("=" * 60)
    
    api = MICPowerAPI()
    
    # 测试服务器连通性
    if not api.test_connectivity():
        print("\n❌ 服务器连接失败，请检查网络连接")
        return
    
    print("\n✅ 服务器连接正常")
    
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
