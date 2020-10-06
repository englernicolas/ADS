import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import AddLibrarianModal from './modals/AddLibrarianModal.js'
import EditLibrarianModal from './modals/EditLibrarianModal.js'
import DeleteLibrarianModal from './modals/DeleteLibrarianModal.js'

export default {
    name: 'Librarians',
    data: () => ({
        librarians: [],
        currentModalTitle: '',
        currentModalWidth: '',
        librarianId: '',
        loadingUsers: false,
        loadingSchools: false,
        loadingGenders: false,
        schools: [],
        genders: [],
        searchText: ''
    }),
    mounted() {
        this.getLibrarians();
        this.getSchools();
        this.getGenders();
        $bus.$on('refresh-librarians', () => {
            this.getLibrarians();
        })
        $bus.$on('refresh-schools', () => {
            this.getSchools();
        })

    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },
        openModal(modalType, id) {
            if (modalType == 'addUser') {
                this.currentModalTitle = 'Adicionar Bibliotecário'
                this.currentModalWidth = '800'
                this.currentModal(AddLibrarianModal)
            }
            if (modalType == 'edit') {
                this.currentModalTitle = 'Editar Bibliotecário'
                this.currentModal(EditLibrarianModal)
                this.currentModalWidth = '800'
                this.$router.push({ path: '/librarians', query: { id: id } })
            }
            if (modalType == 'delete') {
                this.currentModalTitle = 'Deletar Bibliotecário'
                this.currentModal(DeleteLibrarianModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/librarians', query: { id: id } })
            }
            
            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
        },
        async getLibrarians() {
            this.loadingUsers = true

            await axios.get('/librarian/list')
                .then((response) => {
                    this.librarians = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por bibliotecários"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },
        async getSchools() {
            this.loadingSchools = true

            await axios.get('/school/list')
                .then((response) => {
                    this.schools = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por escolas"
                })
                .finally(() => {
                    this.loadingSchools = false
                })
        },
        async getGenders() {
            this.loadingGenders = true

            await axios.get('/gender/list')
                .then((response) => {
                    this.genders = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por gêneros"
                })
                .finally(() => {
                    this.loadingGenders = false
                })
        },
        async getSearchLibrarians() {
            this.loadingUsers = true

            await axios.get(`/librarian/getBySearch?searchText=${this.searchText}`)
                .then((response) => {
                    this.librarians = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por bibliotecários"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Bibliotecários</span></div>
                                
            <v-divider></v-divider>
            
            <div class="d-flex mt-5">
                <v-btn class="ml-16" color="secondary" @click="openModal('addUser')">
                    Adicionar
                    <v-icon right dark>mdi-account-plus</v-icon>
                </v-btn>

                <v-spacer></v-spacer>

                <div class="d-flex mr-16">
                    <v-text-field v-model="searchText" color="teal" placeholder="Pesquisar..." hide-details dense filled clearable></v-text-field>
                    <v-btn @click="getSearchLibrarians" color="primary"><v-icon color="white">mdi-magnify</v-icon></v-btn>          
                </div>
            </div>

            <v-card v-for="librarian in librarians" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Nome:</span>
                            </v-row>
                            <v-row>
                                <span>{{librarian.firstName}} {{librarian.lastName}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">E-mail:</span>
                            </v-row>
                            <v-row>
                                <span style="word-break: break-all; margin-right: 5px">{{librarian.email}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{genders.find(it => it.id == librarian.genderId)?.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Nasc.:</span>
                            </v-row>
                            <v-row>
                                <span>{{librarian.birthDate}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Escola:</span>
                            </v-row>
                            <v-row>
                                <span>{{schools.find(it => it.id == librarian.schoolId)?.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">CPF:</span>
                            </v-row>
                            <v-row>
                                <span>{{librarian.cpf}}</span>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('edit', librarian.id)" class="teal--text d-block">
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn icon @click="openModal('delete', librarian.id)" class="red--text d-block">
                                <v-icon>mdi-delete</v-icon>
                            </v-btn>
                        </div>
                    </v-row>
                </v-container>
            </v-card>

            <div v-if="librarians.length == 0 " class="mt-16 text-center">
                <v-icon large color="grey--text text--darken-4">mdi-magnify-close</v-icon>
                <span class="grey--text text--darken-2 text-h5 font-weight-bold">Sem Resultados!</span>
            </div>

            <modal-template :title="currentModalTitle" :maxWidth="currentModalWidth">
                <modal v-if="this.currentModalTitle == 'Deletar Bibliotecário'"/>
                <modal v-if="this.currentModalTitle == 'Adicionar Bibliotecário'" :schools="schools" :genders="genders"/>
                <modal v-if="this.currentModalTitle == 'Editar Bibliotecário'" :schools="schools" :genders="genders"/>
            </modal-template>
            
        </div>`
}