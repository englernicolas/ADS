import Layout from "/bibliotech/assets/components/Layout.js"
Vue.component("Layout", Layout)

const home = {
    template: `<h1>HOME</h1>`
}

const aa = {
    template: `<h1>AAAAA</h1>`
}

const router = new VueRouter ({
    routes: [
        {
            path: '/home',
            component: home
        },
        {
            path: '/myAccount',
            component: aa
        },
        {
            path: '/students',
            component: home
        },
        {
            path: '/librarians',
            component: aa
        },
        {
            path: '/books',
            component: home
        },
        {
            path: '/loans',
            component: home
        },
    ],
})

var app = new Vue({
    router,
    el: '#app',
    vuetify: new Vuetify(),
    components: {
        'layout': Layout
    },
    data: () => ({

    })

})