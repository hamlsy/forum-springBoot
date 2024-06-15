import Vue from 'vue';
import VueRouter from 'vue-router';
import Login from "../components/LoginForm.vue";
import Register from "../components/RegisterForm.vue";
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
    }
];

const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
});

export default router;