import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

import SearchBox from './utils/SearchBox.js'
Vue.component('SearchBox', SearchBox)

/* MODAIS */
import AddLoanModal from './modals/AddLoanModal.js'
import EditLoanModal from './modals/EditLoanModal.js'
import DeleteLoanModal from './modals/DeleteLoanModal.js'
import PayLoanModal from './modals/PayLoanModal.js'
import DeliverLoanModal from './modals/DeliverLoanModal.js'

export default {
    name: 'Loans',
    data: () => ({
        loans: [
            {
                id: 1,
                book: "O casamento",
                student: "Nicholas Sparks",
                loanDate: "15/03/2002",
                deliveryDate: "15/04/2002",
                debt: '12',
            },
            {
                id: 2,
                book: "O casamento",
                student: "Nicholas Sparks",
                loanDate: "15/03/2002",
                deliveryDate: "15/04/2002",
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
            if (modalType == 'addBook') {
                this.currentModalTitle = 'Adicionar Empréstimo'
                this.currentModalWidth = '800'
                this.currentModal(AddLoanModal)
            }
            if (modalType == 'edit') {
                this.currentModalTitle = 'Editar Empréstimo'
                this.currentModal(EditLoanModal)
                this.currentModalWidth = '800'
                this.$router.push({ path: '/loans', query: { id: id } })
            }
            if (modalType == 'delete') {
                this.currentModalTitle = 'Deletar Empréstimo'
                this.currentModal(DeleteLoanModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/loans', query: { id: id } })
            }
            if (modalType == 'pay') {
                this.currentModalTitle = 'Realizar pagamento?'
                this.currentModal(PayLoanModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/loans', query: { id: id } })
            }
            if (modalType == 'deliver') {
                this.currentModalTitle = 'Realizar entrega?'
                this.currentModal(DeliverLoanModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/loans', query: { id: id } })
            }

            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
        }
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Empreśtimos</span></div>
                                
            <v-divider></v-divider>
            
            <div class="d-flex mt-5">
                <v-btn class="ml-16" color="secondary" @click="openModal('addBook')">
                    Adicionar
                    <v-icon right dark>mdi-book-plus-multiple</v-icon>
                </v-btn>

                <v-spacer></v-spacer>

                <search-box class="mr-16"></search-box>
            </div>

            <v-card v-for="loan in loans" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Livro:</span>
                            </v-row>
                            <v-row>
                                <span>{{loan.book}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Aluno:</span>
                            </v-row>
                            <v-row>
                                <span>{{loan.student}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Emp:</span>
                            </v-row>
                            <v-row>
                                <span>{{loan.loanDate}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Ent:</span>
                            </v-row>
                            <v-row>
                                <span>{{loan.deliveryDate}}</span>
                            </v-row>
                        </v-col>
                        <v-col v-if="loan.debt">
                            <v-row>
                                <span class="font-weight-bold red--text">Multa:</span>
                            </v-row>
                            <v-row>
                                <span class="red--text">R$ {{loan.debt}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <v-btn @click="openModal('pay', loan.id)" v-if="loan.debt" color="primary" @click="openModal('loans', user.id)">
                                    Pagar
                                </v-btn>
                                <v-btn @click="openModal('deliver', loan.id)" v-if="!loan.debt" color="secondary" @click="openModal('loans', user.id)">
                                    Entregar
                                </v-btn>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('edit', loan.id)" class="teal--text d-block">
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn icon @click="openModal('delete', loan.id)" class="red--text d-block">
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