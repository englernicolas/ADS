export default {
    name: 'Layout',
    props: {
      fullName: String
    },
    data() {
        return {
            menuItems: [
                { title: 'Home', icon: 'mdi-home', path: '/home'},
                { title: 'Alunos', icon: 'mdi-account-group-outline', path: '/students'},
                { title: 'Bibliotecários', icon: 'mdi-account-group', path: '/librarians'},
                { title: 'Livros', icon: 'mdi-book-open-page-variant', path: '/books'},
                { title: 'Empréstimos', icon: 'mdi-book', path: '/loans'},
                { title: 'Relatórios', icon: 'mdi-file-chart', path: '/reports'},
            ],
            dropdownItems: [
                { title: 'Editar perfil', path: '/profile'},
                { title: 'Logout', path: '/home'} // FIXME - ALTERAR PATH QUANDO FOR IMPLEMENTADO O SISTEMA DE SESSÃO!!!
            ],
        }
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
                                    <v-list-item v-for="(item, index) in dropdownItems" :key="index" @click="$router.push(item.path)">
                                        <v-list-item-title :class="[item.title == 'Logout' ? 'red--text text--accent-4' : 'grey--text text--darken-3']">{{ item.title }}</v-list-item-title>
                                    </v-list-item>
                                </v-list>
                            </v-menu>
                        </div>
                    </v-app-bar>
                    
                    <v-navigation-drawer absolute permanent clipped app>
                        <v-list dense>
                            <v-list-item v-for="item in menuItems" :key="item.title" @click="$router.push(item.path)">
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