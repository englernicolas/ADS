import routes from "./routes.js"

import Layout from "./components/Layout.js"
Vue.component("Layout", Layout)

const router = new VueRouter ({routes})

Vue.use(VueMask.VueMaskPlugin)

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