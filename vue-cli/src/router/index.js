import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from "../components/LoginForm.vue";
import Register from "../components/RegisterForm.vue";
import PostList from "@/components/PostList.vue";
import PostDetail from "@/components/PostDetail.vue";
Vue.use(VueRouter);

const routes = [
    {
        path:'/',
        redirect: "/login",
    },
    {
        path: '/login',
        name: 'Login',
        component: Login
    },
    {
        path: '/register',
        name: 'Register',
        component: Register
    },
    {
        path: '/post/list',
        name: 'PostList',
        component: PostList
    },
    {
        path: '/post/detail',
        name: 'PostDetail',
        component: PostDetail
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;