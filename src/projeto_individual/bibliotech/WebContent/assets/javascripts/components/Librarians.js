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
        users: [
            {
                id: 1,
                name: "Nicolas",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                cpf: '12313213213',
            },
            {
                id: 2,
                name: "Amigão",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                cpf: '12313213213',
            },
            {
                id: 3,
                name: "Opa",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                cpf: '12313213213',
            },
        ],
        currentModalTitle: '',
        currentModalWidth: ''
    }),
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
        }
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

                <search-box class="mr-16"></search-box>
            </div>

            <v-card v-for="user in users" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Nome:</span>
                            </v-row>
                            <v-row>
                                <span>{{user.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">E-mail:</span>
                            </v-row>
                            <v-row>
                                <span>{{user.email}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{user.gender}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Nasc.:</span>
                            </v-row>
                            <v-row>
                                <span>{{user.birthDate}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">CPF:</span>
                            </v-row>
                            <v-row>
                                <span>{{user.cpf}}</span>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('edit', user.id)" class="teal--text d-block">
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn icon @click="openModal('delete', user.id)" class="red--text d-block">
                                <v-icon>mdi-delete</v-icon>
                            </v-btn>
                        </div>
                    </v-row>
                </v-container>
            </v-card>

            <modal-template :title="currentModalTitle" :maxWidth="currentModalWidth">
                <modal/>
            </modal-template>
            
        </div>`
}