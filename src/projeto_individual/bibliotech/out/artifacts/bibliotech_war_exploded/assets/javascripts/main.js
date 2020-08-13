import routes from "/bibliotech/assets/javascripts/routes.js"

import Layout from "/bibliotech/assets/javascripts/components/Layout.js"
Vue.component("Layout", Layout)

const router = new VueRouter ({routes})

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