export default {
    name: 'Layout',
    data() {
        return {
            menuItems: [
                { title: 'Estudantes', icon: 'mdi-account-group-outline', path: '/students', usersAllowed: [1,2]},
                { title: 'Bibliotecários', icon: 'mdi-account-group', path: '/librarians', usersAllowed: [1]},
                { title: 'Livros', icon: 'mdi-book-open-page-variant', path: '/books', usersAllowed: [1,2,3]},
                { title: 'Empréstimos', icon: 'mdi-book', path: '/loans', usersAllowed: [1,2,3]},
                { title: 'Relatórios', icon: 'mdi-file-chart', path: '/reports', usersAllowed: [1]},
            ],
            dropdownItems: [
                { title: 'Editar perfil', path: '/profile', usersAllowed: [3]},
                { title: 'Logout', path: '/home', usersAllowed: [1,2,3]}
            ],
            fullName: '',
            userType: '',
            loggedUser: {}
        }
    },
    mounted() {
        this.loggedUser = auth.user
        this.userType = auth.user.userTypeId
        this.fullName =  auth.user.firstName + " " + auth.user.lastName
    },
    methods: {
        async logout() {
            await auth.logout()
            this.$router.push('/login')
        },
    },
    template: /*html*/ `<div>
                    <v-app-bar color="primary" flat app clipped-left>
                        <img width="180" src="./assets/images/horizontal_white_logo.png" alt="Logo Bibliotech">
                        
                        <v-spacer></v-spacer>
                        
                        <div class="text-center">
                            <v-menu offset-y>
                                <template v-slot:activator="{ on, attrs }">
                                    <v-btn class="white--text" color="#0d8c7f" v-bind="attrs" v-on="on" text>
                                        {{ fullName }}
                                        <v-icon>mdi-menu-down</v-icon>
                                    </v-btn>
                                </template>
                                <v-list>
                                    <v-list-item v-if="item.usersAllowed.includes(userType)"  v-for="(item, index) in dropdownItems" :key="index" @click="$router.push(item.path)">
                                        <v-list-item-title v-if="item.title == 'Logout'" @click="logout" class="red--text text--accent-4">{{ item.title }}</v-list-item-title>
                                        <v-list-item-title v-else class="grey--text text--darken-3">{{ item.title }}</v-list-item-title>
                                    </v-list-item>
                                </v-list>
                            </v-menu>
                        </div>
                    </v-app-bar>
                    
                    <v-navigation-drawer absolute permanent clipped app>
                        <v-list dense>
                            <v-list-item v-if="item.usersAllowed.includes(userType)" v-for="item in menuItems" :key="item.title" @click="$router.push(item.path)">
                                <v-list-item-icon>
                                    <v-icon>{{ item.icon }}</v-icon>
                                </v-list-item-icon>
                            
                                <v-list-item-content>
                                    <v-list-item-title>{{ item.title }}</v-list-item-title>
                                </v-list-item-content>
                            </v-list-item>
                        </v-list>
                    </v-navigation-drawer>
                    
                    <v-main>
                        <v-container>
                            <slot/>
                        </v-container> 
                    </v-main>      
                </div>`
}