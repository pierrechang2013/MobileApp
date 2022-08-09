1.mongodb.service.ts 屏蔽了构造函数里的连接初始化
2.server的weather.controller.ts 取消了this.authHandler

**Angular兄弟组件之间的交互,这样才能不是每个路由都去请求数据。最后有时间再做
=======>已经做了，使用data.service.ts做公共服务，用emitevent和subject做订阅，传递数据，但是原理不知道。而且点两下才能触发
