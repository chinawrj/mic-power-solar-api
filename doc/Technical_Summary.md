# MIC POWER APP - 技术摘要

## 快速概览

**应用**: MIC POWER 光伏监控  
**协议**: HTTP (非HTTPS)  
**服务器**: http://server-bwn.shuoxd.com:8080/v1/  
**签名**: MD5 + 固定密钥  

## 关键API端点

### 认证相关
- `user/login` - 登录 (敏感)
- `user/modifyPassword` - 改密码 (敏感)
- `user/retrievePassword` - 找回密码 (敏感)

### 设备数据
- `station/getBatteryData` - 电池数据
- `station/getInverterData` - 逆变器数据
- `station/stationList` - 电站列表

### 故障管理
- `station/faultList/{page}` - 故障列表
- `station/realfaultList/{page}` - 实时故障

## 安全状况

❌ **HTTP明文传输**  
❌ **凭据未加密**  
❌ **设备数据暴露**  
✅ **请求签名验证**  

## 签名机制

```
签名密钥: 1f80ca5871919371ea71716cae4841bd
算法: MD5(sorted_params + key)
```

## 生产就绪工具

### MICPowerAPIClient
- **文件**: `mic_power_api_client.py`  
- **状态**: ✅ 生产就绪
- **功能**: 完整的API客户端库

### 测试脚本
- `test_api.py` - 基础测试
- `comprehensive_api_test.py` - 全面测试  
- `simple_test.py` - 简单验证

## 快速验证

```bash
# 安装依赖
pip install requests>=2.25.0

# 设置环境变量
export MIC_EMAIL="your_email@example.com"
export MIC_PASSWORD="your_password"

# 运行客户端
python mic_power_api_client.py
```
