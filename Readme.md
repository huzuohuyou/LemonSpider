This is for chaojijiangshi web version
各模块主要说明：
support:: 各种script、shell、sql、manual等
modules:: 各个主要的主干上的模块、分支，以及包括core在内的模块
extension:: 各种衍生的，或者额外的处理模块，如爬虫、文档处理、视频处理、监控程序、学校定制等。
examples:: 其它不属于modules、extension的项目，如学习类的项目、参考的项目、更老的项目。

Offical Site: http://www.chaojijiangshi.cn


版本说明：
v3.0


v2.1 (2016/03/15)
1. 把pc web的所有剩下未h5的页面，全部增加h5页面的版本
2. 把controller层重构，简化controller层，把具体功能的实现交给module代理来完成。controller专注完成从客户端请求的url跳转到后续页面或者返回json的工作。最终目标使得前端人员能够看懂controller层。实现前后端分离，独立开发。


v2.0.1
1. v2.0的bugfix
2. 增加了闯关模式的班级


v2.0
1. 完成了管理员功能
2. 完成了主要功能h5化，奠定了之后mobile页面优先的规则
3. 班级功能支持了h5，并嵌入到app和微信页面中


v1.0
1. 完成网站的基本功能，主要服务老师，老师可以借助我们的平台完成资源的上传、管理，以班级为单位的教学.
2. 完成了资料库
3. 完成了班级
4. 完成了个人中心
5. 完成了常见的用户登录、注册、角色等。
6. 完成了简单的学生以课程为单位的学习。

