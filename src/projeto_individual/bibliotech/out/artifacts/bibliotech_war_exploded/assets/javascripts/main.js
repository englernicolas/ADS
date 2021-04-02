import pages from "./routes.js"

let routes = []

pages.getRoutes.map(page => {
	const { name, path, component, redirect, meta } = page

	let pathObj = {
		name,
		path,
		component,
		meta: meta || {}
	}

	if (redirect) {
		pathObj.redirect = redirect
	}

	routes.push(pathObj)
})

const router = new VueRouter({routes})

router.beforeEach((to, from, next) => {
	const requiresAuth = to.meta.requiresAuth

	if (requiresAuth && !auth.isLoggedIn()) {
		next({ name: 'login' })
	}
	else if (!requiresAuth && auth.isLoggedIn()) {
		next({ name: 'books' })
	}
	else {
		next()
	}
})

import Layout from "./components/Layout.js"
Vue.component("Layout", Layout)

Vue.use(VueMask.VueMaskPlugin)

axios.defaults.baseURL = 'http://localhost:8080/bibliotech/rest'

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