import MyAccount from "./components/EditProfile.js"
import Students from "./components/Students.js"
import Librarians from "./components/Librarians.js"
import Books from "./components/Books.js"
import Loans from "./components/Loans.js"
import Reports from "./components/Reports.js"
import Login from "./components/Login.js"

export default new Vue({
    computed: {
        getRoutes() {
            const routes =  [
                {
                    path: '*',
                    redirect: '/'
                },
                {
                    path: '/login',
                    meta: {
                        requiresAuth: false,
                    },
                    name: 'login',
                    component: Login
                }
            ]

            if (auth.loggedIn) {
                if (auth.user.userType == 1) {
                    routes.push(
                        {
                            path: '/profile',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'profile',
                            component: MyAccount
                        },
                        {
                            path: '/students',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'students',
                            component: Students
                        },
                        {
                            path: '/librarians',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'librarians',
                            component: Librarians
                        },
                        {
                            path: '/books',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'books',
                            component: Books
                        },
                        {
                            path: '/loans',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'loans',
                            component: Loans
                        },
                        {
                            path: '/reports',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'reports',
                            component: Reports
                        },
                    )
                } else if (auth.user.userType == 2) {
                    routes.push(
                        {
                            path: '/profile',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'profile',
                            component: MyAccount
                        },
                        {
                            path: '/students',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'students',
                            component: Students
                        },
                        {
                            path: '/librarians',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'librarians',
                            component: Librarians
                        },
                        {
                            path: '/books',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'books',
                            component: Books
                        },
                        {
                            path: '/loans',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'loans',
                            component: Loans
                        },
                    )
                } else {
                    routes.push(
                        {
                            path: '/profile',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'profile',
                            component: MyAccount
                        },
                        {
                            path: '/books',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'books',
                            component: Books
                        },
                        {
                            path: '/loans',
                            meta: {
                                requiresAuth: true,
                            },
                            name: 'loans',
                            component: Loans
                        },
                    )
                }
            }
            return routes
        }
    }
})