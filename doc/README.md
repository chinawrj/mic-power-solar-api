# API 测试脚本使用说明

## 📁 文件说明

### 1. test_api.py - 完整测试脚本
- 🔧 包含完整的API测试功能
- 🔐 支持登录验证
- 📊 详细的调试信息输出
- 🏭 电站列表获取测试

### 2. quick_test.py - 快速测试脚本  
- ⚡ 专门测试电站列表接口
- 🎯 简化版本，快速验证
- 📝 直接输入邮箱测试

### 3. requirements.txt - 依赖包
- 📦 Python依赖包列表

## 🚀 使用方法

### 环境准备
```bash
# 安装Python依赖
pip install -r requirements.txt

# 或者直接安装requests
pip install requests
```

### 运行完整测试
```bash
cd c:\Users\china\guangfu\doc
python test_api.py
```

### 运行快速测试
```bash
cd c:\Users\china\guangfu\doc  
python quick_test.py
```

## 📋 测试流程

### 完整测试 (test_api.py)
1. 🌐 检查服务器连通性
2. 📧 输入邮箱和密码
3. 🔑 测试用户登录
4. 🏭 获取电站列表
5. 📊 显示结果统计

### 快速测试 (quick_test.py)
1. 📧 输入邮箱地址
2. 🔐 自动生成API签名
3. 📡 直接调用电站列表接口
4. 📊 显示结果

## 🔍 预期结果

### 成功响应示例
```json
{
  "result": 0,
  "msg": "success", 
  "obj": [
    {
      "id": 12345,
      "stationName": "我的光伏电站",
      "overviewType": "residential"
    }
  ]
}
```

### 错误响应示例
```json
{
  "result": 1,
  "msg": "Invalid signature",
  "obj": null
}
```

## 🛠️ 调试信息

脚本会输出以下调试信息：
- 📝 请求URL和参数
- 🔐 MD5签名计算过程
- 📡 HTTP请求/响应详情
- 📊 JSON响应解析结果

## ⚠️ 注意事项

1. **网络连接**: 确保能够访问 `server-bwn.shuoxd.com:8080`
2. **账户有效性**: 需要有效的MIC POWER账户
3. **签名算法**: 使用MD5(参数+固定密钥)签名
4. **协议安全**: 该API使用HTTP明文传输
5. **安全合规**: 不要在代码中硬编码邮箱或密码。请通过环境变量提供：
  - MIC_EMAIL: 登录邮箱
  - MIC_PASSWORD: 登录密码

## 🔧 故障排除

### 连接失败
```
❌ 服务器连接失败，请检查网络连接
```
**解决**: 检查网络连接和防火墙设置

### 签名错误  
```
❌ API返回错误: Invalid signature
```
**解决**: 检查参数拼接和签名计算逻辑

### 认证失败
```
❌ 登录失败: Invalid credentials
```
**解决**: 检查邮箱和密码是否正确

### JSON解析错误
```
⚠️ 响应不是有效JSON
```
**解决**: 检查服务器响应格式

## 📞 技术支持

如遇到问题，请检查：
1. Python版本 (建议3.7+)
2. requests库版本
3. 网络连接状态
4. 账户凭据有效性

---
**免责声明**: 这些脚本仅用于安全研究和教育目的，请在合法授权范围内使用。
