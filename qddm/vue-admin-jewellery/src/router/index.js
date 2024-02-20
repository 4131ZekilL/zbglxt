import Vue from 'vue'
import Router from 'vue-router'

// in development-env not use lazy-loading, because lazy-loading too many pages will cause webpack hot update too slow. so only in production use lazy-loading;
// detail: https://panjiachen.github.io/vue-element-admin-site/#/lazy-loading

Vue.use(Router)

/* Layout */
import Layout from '../views/layout/Layout'

/**
* hidden: true                   if `hidden:true` will not show in the sidebar(default is false)
* alwaysShow: true               if set true, will always show the root menu, whatever its child routes length
*                                if not set alwaysShow, only more than one route under the children
*                                it will becomes nested mode, otherwise not show the root menu
* redirect: noredirect           if `redirect:noredirect` will no redirect in the breadcrumb
* name:'router-name'             the name is used by <keep-alive> (must set!!!)
* meta : {
    title: 'title'               the name show in submenu and breadcrumb (recommend set)
    icon: 'svg-name'             the icon show in the sidebar,
  }
**/
export const constantRouterMap = [
  { path: '/login', component: () => import('@/views/login/index'), hidden: true },
  { path: '/404', component: () => import('@/views/404'), hidden: true },

  {
    path: '/',
    component: Layout,
    redirect: '/dashboard',
    name: 'Dashboard',
    hidden: true,
    children: [{
      path: 'dashboard',
      component: () => import('@/views/dashboard/index')
    }]
  },
  {
    path: '/designer',
    component: Layout,
    redirect: '/designer/table',
    name: '设计师管理',
    meta: { title: '设计师管理', icon: 'example' },
    children: [
      {
        path: 'table',
        name: '设计师列表',
        component: () => import('@/views/jewellery/designer/list'),
        meta: { title: '设计师列表', icon: 'table' }
      },
      {
        path: 'add',
        name: '添加设计师',
        component: () => import('@/views/jewellery/designer/addDesigner'),
        meta: { title: '添加设计师', icon: 'tree' }
      },
      {
        path: 'edit/:id',
        name: '编辑设计师',
        component: () => import('@/views/jewellery/designer/addDesigner'),
        meta: { title: '编辑设计师', icon: 'tree' },
        hidden:true
      }
    ]
  },
  {
    path: '/classofy',
    component: Layout,
    redirect: '/classofy/list',
    name: '珠宝分类管理',
    meta: { title: '珠宝分类管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '珠宝分类列表',
        component: () => import('@/views/jewellery/classofy/list'),
        meta: { title: '珠宝分类列表', icon: 'table' }
      },
      {
        path: 'add',
        name: '添加珠宝分类',
        component: () => import('@/views/jewellery/classofy/addClassofy'),
        meta: { title: '添加珠宝分类', icon: 'tree' }
      }
    ]
  },
  {
    path: '/jewellery',
    component: Layout,
    redirect: '/jewellery/list',
    name: '珠宝管理',
    meta: { title: '珠宝管理', icon: 'example' },
    children: [
      {
        path: 'list',
        name: '珠宝列表',
        component: () => import('@/views/jewellery/jewellery/list'),
        meta: { title: '珠宝列表', icon: 'table' }
      },
      {
        path: 'add',
        name: '添加珠宝',
        component: () => import('@/views/jewellery/jewellery/info'),
        meta: { title: '添加珠宝', icon: 'tree' }
      },
      {
        path: 'info/:id',
        name: 'JewelleryInfoEdit',
        component: () => import('@/views/jewellery/jewellery/info'),
        meta: { title: '编辑珠宝基本信息', noCache: true },
        hidden: true
      },
      {
        path: 'chapter/:id',
        name: 'JewelleryChapterEdit',
        component: () => import('@/views/jewellery/jewellery/chapter'),
        meta: { title: '添加大纲', noCache: true },
        hidden: true
      },
      
      {
        path: 'publish/:id',
        name: 'JewelleryPublishEdit',
        component: () => import('@/views/jewellery/jewellery/publish'),
        meta: { title: '发布珠宝', noCache: true },
        hidden: true
      }
    ]
  },
  {
    path: '/example',
    component: Layout,
    redirect: '/example/table',
    name: 'Example',
    meta: { title: 'Example', icon: 'example' },
    children: [
      {
        path: 'table',
        name: 'Table',
        component: () => import('@/views/table/index'),
        meta: { title: 'Table', icon: 'table' }
      },
      {
        path: 'tree',
        name: 'Tree',
        component: () => import('@/views/tree/index'),
        meta: { title: 'Tree', icon: 'tree' }
      }
    ]
  },

  {
    path: '/form',
    component: Layout,
    children: [
      {
        path: 'index',
        name: 'Form',
        component: () => import('@/views/form/index'),
        meta: { title: 'Form', icon: 'form' }
      }
    ]
  },

  {
    path: '/nested',
    component: Layout,
    redirect: '/nested/menu1',
    name: 'Nested',
    meta: {
      title: 'Nested',
      icon: 'nested'
    },
    children: [
      {
        path: 'menu1',
        component: () => import('@/views/nested/menu1/index'), // Parent router-view
        name: 'Menu1',
        meta: { title: 'Menu1' },
        children: [
          {
            path: 'menu1-1',
            component: () => import('@/views/nested/menu1/menu1-1'),
            name: 'Menu1-1',
            meta: { title: 'Menu1-1' }
          },
          {
            path: 'menu1-2',
            component: () => import('@/views/nested/menu1/menu1-2'),
            name: 'Menu1-2',
            meta: { title: 'Menu1-2' },
            children: [
              {
                path: 'menu1-2-1',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-1'),
                name: 'Menu1-2-1',
                meta: { title: 'Menu1-2-1' }
              },
              {
                path: 'menu1-2-2',
                component: () => import('@/views/nested/menu1/menu1-2/menu1-2-2'),
                name: 'Menu1-2-2',
                meta: { title: 'Menu1-2-2' }
              }
            ]
          },
          {
            path: 'menu1-3',
            component: () => import('@/views/nested/menu1/menu1-3'),
            name: 'Menu1-3',
            meta: { title: 'Menu1-3' }
          }
        ]
      },
      {
        path: 'menu2',
        component: () => import('@/views/nested/menu2/index'),
        meta: { title: 'menu2' }
      }
    ]
  },

  {
    path: 'external-link',
    component: Layout,
    children: [
      {
        path: 'https://panjiachen.github.io/vue-element-admin-site/#/',
        meta: { title: 'External Link', icon: 'link' }
      }
    ]
  },

  { path: '*', redirect: '/404', hidden: true }
]

export default new Router({
  // mode: 'history', //后端支持可开
  scrollBehavior: () => ({ y: 0 }),
  routes: constantRouterMap
})
