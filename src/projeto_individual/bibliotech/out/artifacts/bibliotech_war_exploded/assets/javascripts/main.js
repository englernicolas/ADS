import routes from "./routes.js"

import Layout from "./components/Layout.js"
Vue.component("Layout", Layout)

const router = new VueRouter ({routes})

Vue.use(VueMask.VueMaskPlugin)

axios.defaults.baseURL = 'http://localhost:8080/bibliotech/rest/'

var app = new Vue({
    router,
    el: '#app',
    vuetify: new Vuetify({
        theme: {
			themes: {
					light: {
							primary: '#52bdae',
							secondary: '#1976d2',
							accent: '#3ea2fb',
							error: '#dc3545',
							petrol: '#17a499',
							background: '#f4f4f4',
					}
			},
			options: {
					customProperties: true
			},
		},
    }),
    components: {
        'layout': Layout
    },
    data: () => ({
    }),
})