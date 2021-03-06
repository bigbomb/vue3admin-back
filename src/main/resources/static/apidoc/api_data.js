define({ "api": [
  {
    "type": "GET",
    "url": "/base/captcha.jpg",
    "title": "",
    "description": "<p>验证码图片</p>",
    "version": "1.0.0",
    "group": "BaseCaptcha",
    "name": "get",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    uuid: 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/captcha/controller/BaseCaptchaController.java",
    "groupTitle": "BaseCaptcha"
  },
  {
    "type": "POST",
    "url": "/base/login",
    "title": "",
    "description": "<p>登录</p>",
    "version": "1.0.0",
    "group": "BaseLogin",
    "name": "login",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    username: 1, // 帐号\n    password: 1, // 密码\n    uuid: 1, // UUID\n    code: 1 // 验证码\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        user_id: 1, // 用户ID\n        token: 1, // token\n        expired_at: 1, // 过期时间\n        updated_at: 1 // 更新时间\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/login/controller/BaseLoginController.java",
    "groupTitle": "BaseLogin"
  },
  {
    "type": "POST",
    "url": "/base/logout",
    "title": "",
    "description": "<p>登录</p>",
    "version": "1.0.0",
    "group": "BaseLogin",
    "name": "logout",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/login/controller/BaseLoginController.java",
    "groupTitle": "BaseLogin"
  },
  {
    "type": "POST",
    "url": "/base/menu/create",
    "title": "create",
    "description": "<p>菜单新增</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "create",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '', // 英文名称\n        url: '', // 路由 、url\n        permission: '', // 权限\n        type: '', // 类型   0：目录   1：菜单   2：按钮\n        icon: '', // 图标\n        sort: '' // 排序\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "POST",
    "url": "/base/menu/delete",
    "title": "delete",
    "description": "<p>菜单删除</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "delete",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    \t   id: '' // ID\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "GET",
    "url": "/base/menu/select",
    "title": "select",
    "description": "<p>菜单下拉选择</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "get",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n    \t   id: '', // ID\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '' // 英文名称\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "GET",
    "url": "/base/menu/info/{id}",
    "title": "info",
    "description": "<p>菜单信息</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "info",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n    \t   id: '', // ID\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '', // 英文名称\n        url: '', // 路由 、url\n        permission: '', // 权限\n        type: '', // 类型   0：目录   1：菜单   2：按钮\n        icon: '', // 图标\n        sort: '', // 排序\n        is_display: '', // 是否显示\n        is_tab: '', // 是否显示在标签栏\n        children: []\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "GET",
    "url": "/base/menu/list",
    "title": "list",
    "description": "<p>菜单列表</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "listByParent",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     parent_id: 0 // 父级ID 根目录为 0\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n    \t   id: '', // ID\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '', // 英文名称\n        url: '', // 路由 、url\n        permission: '', // 权限\n        type: '', // 类型   0：目录   1：菜单   2：按钮\n        icon: '', // 图标\n        sort: '', // 排序\n        is_display: '', // 是否显示\n        is_tab: '', // 是否显示在标签栏\n        children: []\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "GET",
    "url": "/base/menu/self",
    "title": "self",
    "description": "<p>当前用户 菜单、权限</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "self",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n         permissions:[\"base:menu:page\", \"base:menu:add\"] // 权限列表\n         menus: [{\n        \t   id: '', // ID\n            parent_id: '', // 父级ID\n            name_cn: '', // 中文名称\n            name_en: '', // 英文名称\n            url: '', // 路由 、url\n            type: '', // 类型   0：目录   1：菜单   2：按钮\n            icon: '', // 图标\n            sort: '', // 排序\n            is_display: '', // 是否显示\n            is_tab: '', // 是否显示在标签栏\n            children: [{\n                 id: '', // ID\n                 parent_id: '', // 父级ID\n                 name_cn: '', // 中文名称\n                 name_en: '', // 英文名称\n                 url: '', // 路由 、url\n                 type: '', // 类型   0：目录   1：菜单   2：按钮\n                 icon: '', // 图标\n                 sort: '', // 排序\n                 is_display: '', // 是否显示\n                 is_tab: '', // 是否显示在标签栏\n                 children：[]\n            }]\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "GET",
    "url": "/base/menu/self/select",
    "title": "selfSelect",
    "description": "<p>菜单下拉选择</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "selfSelect",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n    \t   id: '', // ID\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '' // 英文名称\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "POST",
    "url": "/base/menu/update",
    "title": "update",
    "description": "<p>菜单新增</p>",
    "version": "1.0.0",
    "group": "BaseMenu",
    "name": "update",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    \t   id: '', // ID\n        parent_id: '', // 父级ID\n        name_cn: '', // 中文名称\n        name_en: '', // 英文名称\n        url: '', // 路由 、url\n        permission: '', // 权限\n        type: '', // 类型   0：目录   1：菜单   2：按钮\n        icon: '', // 图标\n        sort: '' // 排序\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/menu/controller/BaseMenuController.java",
    "groupTitle": "BaseMenu"
  },
  {
    "type": "POST",
    "url": "/base/role/create",
    "title": "create",
    "description": "<p>新增</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "create",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     name: '', // 角色名称\n     remark: '', // 备注\n     menu_ids: [] // 菜单权限ID\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "POST",
    "url": "/base/role/delete",
    "title": "delete",
    "description": "<p>批量删除</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "delete",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     ids: [], // ID\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "GET",
    "url": "/base/role/info/{id}",
    "title": "info",
    "description": "<p>角色信息</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "info",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n         id: '', // ID\n         name: '', // 角色名\n         remark: '', // 备注\n         created_at: '' // 创建时间\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "GET",
    "url": "/base/role/page",
    "title": "page",
    "description": "<p>角色列表</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "page",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    current: 1, // 当前页\n    size: 10 // 页面大小\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        current: 1, // 当前页\n        size: 1, // 页面大小\n        total: 1, // 总条数\n        pages: 1, // 总页数\n        list: [{\n        \t   id: '', // ID\n            name: '', // 角色名\n            remark: '', // 备注\n            created_at: '' // 创建时间\n        }]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "POST",
    "url": "/base/role/select",
    "title": "select",
    "description": "<p>角色下拉</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "select",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'，\n    data: [{\n         id: '', // ID\n         name: '', // 角色名称\n         remark: '', // 备注\n    }]\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "POST",
    "url": "/base/role/update",
    "title": "update",
    "description": "<p>编辑</p>",
    "version": "1.0.0",
    "group": "BaseRole",
    "name": "update",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     id: '', // ID\n     name: '', // 角色名称\n     remark: '', // 备注\n     menu_ids: [] // 菜单权限ID\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/role/controller/BaseRoleController.java",
    "groupTitle": "BaseRole"
  },
  {
    "type": "POST",
    "url": "/base/user/create",
    "title": "create",
    "description": "<p>新增</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "create",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n         username: '', // 用户名\n         password: '', // 密码\n         nickname: '', // 昵称\n         mobile: '', // 手机\n         email: '', // 邮箱\n         avatar: '' // 头像\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "POST",
    "url": "/base/user/delete",
    "title": "delete",
    "description": "<p>批量删除</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "delete",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n     ids: [], // ID\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "GET",
    "url": "/base/user/info/{id}",
    "title": "info",
    "description": "<p>系统用户信息</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "info",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n         id: '', // ID\n         username: '', // 用户名\n         nickname: '', // 昵称\n         mobile: '', // 手机\n         email: '', // 邮箱\n         avatar: '', // 头像\n         status: '', // 状态\n         roles: [{\n             id: '', // 角色ID\n             name: '', // 角色名称\n             remark: '' // 角色备注\n         }]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "GET",
    "url": "/base/user/page",
    "title": "page",
    "description": "<p>系统用户列表</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "page",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    current: 1, // 当前页\n    size: 10 // 页面大小\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        current: 1, // 当前页\n        size: 1, // 页面大小\n        total: 1, // 总条数\n        pages: 1, // 总页数\n        list: [{\n        \t   id: '', // ID\n            username: '', // 用户名\n            nickname: '', // 昵称\n            mobile: '', // 手机\n            email: '', // 邮箱\n            avatar: '', // 头像\n            status: '', // 状态\n            roles: [{\n                id: '', // 角色ID\n                name: '', // 角色名称\n                remark: '' // 角色备注\n            }]\n        }]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "GET",
    "url": "/base/schedule/log/page",
    "title": "page",
    "description": "<p>系统用户列表</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "page",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    current: 1, // 当前页\n    size: 10, // 页面大小\n    bean_name: \"\" // spring bean名称\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        current: 1, // 当前页\n        size: 1, // 页面大小\n        total: 1, // 总条数\n        pages: 1, // 总页数\n        list: [{\n        \t   id: '', // ID\n            task_id: '', // 任务id\n            bean_name: '', // spring bean名称\n            params: '', // 参数\n            status: '', // 任务状态 0：失败 1：成功\n            message: '', // 成功信息\n            error: '', // 失败信息\n            times: '', // 耗时(单位：毫秒)\n            created_at: ‘’ // 创建时间\n        }]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/quartz/controller/BaseScheduleTaskLogController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "GET",
    "url": "/base/schedule/task/page",
    "title": "page",
    "description": "<p>系统用户列表</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "page",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n    current: 1, // 当前页\n    size: 10, // 页面大小\n    bean_name: \"\" // spring bean名称\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        current: 1, // 当前页\n        size: 1, // 页面大小\n        total: 1, // 总条数\n        pages: 1, // 总页数\n        list: [{\n        \t   id: '', // ID\n            task_id: '', // 任务id\n            bean_name: '', // spring bean名称\n            params: '', // 参数\n            status: '', // 任务状态 0：失败 1：成功\n            message: '', // 成功信息\n            error: '', // 失败信息\n            times: '', // 耗时(单位：毫秒)\n            created_at: ‘’ // 创建时间\n        }]\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/quartz/controller/BaseScheduleTaskLogController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "GET",
    "url": "/base/user/self",
    "title": "self",
    "description": "<p>当前登录用户信息</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "self",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n    data: {\n        \t   id: '', // ID\n            username: '', // 用户名\n            nickname: '', // 昵称\n            mobile: '', // 手机\n            email: '', // 邮箱\n            avatar: '', // 头像\n            status: '', // 状态\n            roles: [{\n                id: '', // 角色ID\n                name: '', // 角色名称\n                remark: '' // 角色备注\n            }]\n        }\n    }\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  },
  {
    "type": "POST",
    "url": "/base/user/update",
    "title": "update",
    "description": "<p>编辑</p>",
    "version": "1.0.0",
    "group": "BaseUser",
    "name": "update",
    "parameter": {
      "examples": [
        {
          "title": "请求参数示例",
          "content": "{\n         id: '', // ID\n         username: '', // 用户名\n         password: '', // 密码\n         nickname: '', // 昵称\n         mobile: '', // 手机\n         email: '', // 邮箱\n         avatar: '' // 头像\n}",
          "type": "json"
        }
      ]
    },
    "success": {
      "examples": [
        {
          "title": "响应结果示例",
          "content": "{\n    code: 0,\n    message: '成功！',\n    status: 'success'\n}",
          "type": "json"
        }
      ]
    },
    "filename": "src/main/java/com/loafer/springboot/modules/base/user/controller/BaseUserController.java",
    "groupTitle": "BaseUser"
  }
] });
