# MIC POWER Solar API Client Documentation

## 📁 项目文件说明

### 核心文件

#### 1. mic_power_api_client.py - 生产就绪API客户端 🔥
- 🏭 完整的MIC POWER光伏监控系统API客户端
- 🔐 支持用户认证和会话管理
- 📊 电站列表、设备数据获取
- 🛠️ 完善的错误处理和日志记录
- � 生产环境就绪

#### 2. test_api.py - API测试脚本
- 🔧 用于验证API功能
- 📈 包含基础的API调用测试

#### 3. comprehensive_api_test.py - 综合测试
- 🧪 全面的API端点测试
- � 深度功能验证

#### 4. requirements.txt - 项目依赖
- 📦 Python依赖包清单

## 🚀 快速开始

### 环境准备
```bash
# 安装Python依赖
pip install -r requirements.txt

# 或者直接安装requests
pip install requests>=2.25.0
```

### 使用生产客户端（推荐）
```bash
# 导入并使用MIC POWER API客户端
python -c "
from mic_power_api_client import MICPowerAPIClient
import os

# 设置环境变量
os.environ['MIC_EMAIL'] = 'your_email@example.com'
os.environ['MIC_PASSWORD'] = 'your_password'

# 创建客户端实例
client = MICPowerAPIClient()

# 登录
if client.login():
    # 获取电站列表
    stations = client.get_station_list()
    print(f'找到 {len(stations)} 个电站')
"
```

### 运行测试脚本
```bash
# 运行基础测试
python test_api.py

# 运行综合测试
python comprehensive_api_test.py
```

## 📋 使用说明

### 环境变量配置
```bash
export MIC_EMAIL="your_email@example.com"
export MIC_PASSWORD="your_password"
```

### MICPowerAPIClient 功能
1. 🔑 用户登录认证
2. 🏭 获取电站列表
3. 📱 获取设备列表
4. 📊 获取设备数据
5. � 完善的错误处理

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
