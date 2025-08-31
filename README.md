# MIC POWER Solar Monitoring System

[![Python](https://img.shields.io/badge/Python-3.7+-blue.svg)](https://python.org)
[![Status](https://img.shields.io/badge/Status-Production%20Ready-green.svg)]()
[![API](https://img.shields.io/badge/API-Reverse%20Engineered-orange.svg)]()

> 基于逆向工程的MIC POWER光伏监控系统API客户端

## 🎯 项目概览

通过对MIC POWER移动应用的深度逆向工程分析，成功破解了其API接口协议，并提供了生产就绪的Python客户端库。

### ✨ 主要特性

- 🔓 **完全破解API签名机制** - MD5 + 固定密钥
- 🔐 **用户认证功能** - 邮箱密码登录
- 🏭 **电站管理** - 获取电站列表和状态
- 📱 **设备监控** - 设备列表和数据获取
- 🛠️ **生产就绪** - 完善的错误处理和日志

## 🚀 快速开始

### 安装依赖

```bash
pip install -r doc/requirements.txt
```

### 基础使用

```python
from doc.mic_power_api_client import MICPowerAPIClient
import os

# 设置环境变量
os.environ['MIC_EMAIL'] = 'your_email@example.com'
os.environ['MIC_PASSWORD'] = 'your_password'

# 创建客户端实例
client = MICPowerAPIClient()

# 登录并获取电站信息
if client.login():
    stations = client.get_station_list()
    print(f"发现 {len(stations)} 个电站")
```

## 📖 文档

- [**API客户端使用说明**](doc/README.md) - 快速开始指南
- [**项目详细文档**](doc/PROJECT_README.md) - 完整的项目说明
- [**API分析报告**](doc/final_api_summary.md) - 深度技术分析
- [**API端点清单**](doc/API_Endpoints.md) - 所有API端点文档

## 🛡️ 发布安全说明

- ✅ 无个人凭据提交到代码库
- ✅ 示例使用占位符或环境变量
- ✅ 敏感信息通过环境变量提供
- ✅ 仅供研究和学习目的

## ⚖️ 法律声明

**重要**: 本项目基于合法的软件逆向工程技术开发，符合中美两国相关法律法规。详细法律声明请参见 [LEGAL_NOTICE.md](LEGAL_NOTICE.md)。

- 🔍 **逆向工程合法性**: 符合学术研究和互操作性目的
- 📚 **仅供学习使用**: 不得用于商业或非法用途  
- 🚫 **免责声明**: 使用者自行承担使用风险
- 📖 **知识产权**: 尊重原软件知识产权

## 📊 项目状态

- ✅ **API逆向工程** - 完成
- ✅ **签名算法破解** - 完成  
- ✅ **Python客户端** - 生产就绪
- ✅ **文档编写** - 完成
- ✅ **测试验证** - 通过

## 🤝 贡献

欢迎提交Issues和Pull Requests来改进这个项目。

## 📜 许可证

本项目采用 MIT License（仅限学术研究和教育用途）。详见 [LICENSE](LICENSE) 文件。

**使用限制**:
- ✅ 学术研究和教育用途
- ✅ 技术学习和互操作性研究  
- ❌ 商业用途（需获得明确许可）
- ❌ 违法或侵权用途

## ⚖️ 完整法律信息

- 📄 **详细法律声明**: [LEGAL_NOTICE.md](LEGAL_NOTICE.md)
- 📋 **使用许可**: [LICENSE](LICENSE)
- 🔍 **逆向工程合规性**: 符合中美法律法规

详细信息请参见 [doc/PROJECT_README.md](doc/PROJECT_README.md) 和 [doc/final_api_summary.md](doc/final_api_summary.md)

---

*最后更新: 2025-08-31*  
*开发者: [@chinawrj](https://github.com/chinawrj)*
