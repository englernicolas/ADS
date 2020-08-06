export default {
    name: 'Layout',
    props: {
      fullName: String
    },
    data() {
        return {
            items: [
                { title: 'Home', icon: 'mdi-home-city' },
                { title: 'Minha conta', icon: 'mdi-account' },
                { title: 'Alunos', icon: 'mdi-account-group-outline' },
                { title: 'Bibliotecários', icon: 'mdi-account-group' },
                { title: 'Livros', icon: 'mdi-book-open-page-variant' },
                { title: 'Empréstimos', icon: 'mdi-book' },
            ],
        }
    },
    template: `<div>
                    <v-app-bar flat app clipped-left>
                        <v-avatar tile><img src="assets/images/logo.png" alt="Logo Bibliotech"></v-avatar>
                                             
                        <v-list-item-content :right="right">
                            <v-list-item-title><a class="black--text">{{this.fullName}}</a></v-list-item-title>
                            <v-list-item-subtitle><a class="grey--text">Logout</a></v-list-item-subtitle>
                        </v-list-item-content>
                    </v-app-bar>
                    
                    <v-navigation-drawer absolute permanent clipped app>
                        <v-list dense>
                            <v-list-item v-for="item in items" :key="item.title" @click="" >
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