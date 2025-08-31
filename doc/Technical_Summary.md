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
签名密钥: <通过环境变量 MIC_SIGNATURE_KEY 提供>
算法: MD5(请求参数 + 密钥)
```

## 验证命令

```bash
# 测试服务器连通性
curl -I http://server-bwn.shuoxd.com:8080

# 抓包分析
# 使用Charles/Wireshark监控HTTP流量
```
