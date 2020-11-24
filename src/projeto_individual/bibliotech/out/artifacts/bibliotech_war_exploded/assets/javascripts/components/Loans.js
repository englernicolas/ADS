import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import AddLoanModal from './modals/AddLoanModal.js'
import DeleteLoanModal from './modals/DeleteLoanModal.js'
import DeliverLoanModal from './modals/DeliverLoanModal.js'

export default {
    name: 'Loans',
    data: () => ({
        loans: [],
        currentModalTitle: '',
        currentModalWidth: '',
        selectedLoan: '',
        studentId: '',
        loadingStudents: false,
        loadingBooks: false,
        loadingLoans: false,
        availableStudents: [],
        allStudents: [],
        allBooks: [],
        availableBooks: [],
        searchText: ''
    }),
    mounted() {
        this.getAllStudents();
        this.getAvailableStudents();
        this.getAllBooks();
        this.getAvailableBooks();
        this.getLoans();
        $bus.$on('refresh-loans', () => {
            this.getLoans();
        })
        $bus.$on('refresh-availableStudents', () => {
            this.getAvailableStudents();
        })
        $bus.$on('refresh-allStudents', () => {
            this.getAllStudents();
        })
        $bus.$on('refresh-allBooks', () => {
            this.getAllBooks();
        })
        $bus.$on('refresh-availableBooks', () => {
            this.getAvailableBooks();
        })

    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },
        openModal(modalType, id, loan) {
            if (modalType == 'addBook') {
                this.currentModalTitle = 'Adicionar Empréstimo'
                this.currentModalWidth = '800'
                this.currentModal(AddLoanModal)
            }
            if (modalType == 'delete') {
                this.selectedLoan = loan
                this.currentModalTitle = 'Deletar Empréstimo'
                this.currentModal(DeleteLoanModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/loans', query: { id: id } })
            }
            if (modalType == 'deliver') {
                this.selectedLoan = loan
                this.currentModalTitle = 'Realizar Devolução?'
                this.currentModal(DeliverLoanModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/loans', query: { id: id } })
            }

            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
        },
        async getAvailableStudents() {
            this.loadingUsers = true

            await axios.get('/student/listNeedVerifyBookQuantity')
                .then((response) => {
                    this.availableStudents = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por estudantes"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },
        async getAllStudents() {
            this.loadingUsers = true

            await axios.get('/student/list')
                .then((response) => {
                    this.allStudents = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por estudantes"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },
        async getAllBooks() {
            this.loadingBooks = true

            await axios.get('/book/list')
                .then((response) => {
                    this.allBooks = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por livros"
                })
                .finally(() => {
                    this.loadingBooks = false
                })
        },
        async getAvailableBooks() {
            this.loadingBooks = true

            await axios.get('/book/listNeedBookAvailability')
                .then((response) => {
                    this.availableBooks = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por livros"
                })
                .finally(() => {
                    this.loadingBooks = false
                })
        },
        async getLoans() {
            this.loadingLoans = true

            await axios.get('/loan/list')
                .then((response) => {
                    this.loans = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por empréstimos"
                })
                .finally(() => {
                    this.loadingLoans = false
                })
        },
        async getSearchLoans() {
            this.loadingLoans = true

            await axios.get(`/loan/getBySearch?searchText=${this.searchText}`)
                .then((response) => {
                    this.loans = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por empréstimos"
                })
                .finally(() => {
                    this.loadingLoans = false
                })
        },
        formatDate (date) {
            if (!date) return null

            const [year, month, day] = date.split('-')
            return `${day}/${month}/${year}`
        },
        isLate (deliveryDate) {
            return new Date(deliveryDate).getTime() < new Date().getTime();
        },
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Empréstimos</span></div>
                                
            <v-divider></v-divider>
            
            <div class="d-flex mt-5">
                <v-btn class="ml-16" color="secondary" @click="openModal('addBook')">
                    Adicionar
                    <v-icon right dark>mdi-book-plus-multiple</v-icon>
                </v-btn>

                <v-spacer></v-spacer>
                
                <div class="d-flex mr-16">
                    <v-text-field v-model="searchText" color="teal" placeholder="Pesquisar..." hide-details dense filled clearable></v-text-field>
                    <v-btn @click="getSearchLoans" color="primary"><v-icon color="white">mdi-magnify</v-icon></v-btn>          
                </div>
            </div>

            <v-card v-for="loan in loans" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Livro(s):</span>
                            </v-row>
                            <v-row v-for="bookId in loan.booksIds">
                                <span>- {{allBooks.find(it => it.id == bookId)?.title}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Estudante:</span>
                            </v-row>
                            <v-row>
                                <span>{{allStudents.find(it => it.id == loan.studentId)?.firstName}} {{allStudents.find(it => it.id == loan.studentId)?.lastName}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Emp:</span>
                            </v-row>
                            <v-row>
                                <span>{{formatDate(loan.loanDate)}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Ent:</span>
                            </v-row>
                            <v-row>
                                <span>{{formatDate(loan.deliveryDate)}}</span>
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
                                <v-btn @click="openModal('deliver', loan.id, loan)" v-if="isLate(loan.deliveryDate)" color="error">
                                    Devolver (Atrasado)
                                </v-btn>
                                <v-btn @click="openModal('deliver', loan.id, loan)" v-else color="secondary">
                                    Devolver
                                </v-btn>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('delete', loan.id, loan)" class="mt-5 red--text d-block">
                                <v-icon>mdi-delete</v-icon>
                            </v-btn>
                        </div>
                    </v-row>
                </v-container>
            </v-card>
            
            <div v-if="loans.length == 0 " class="mt-16 text-center">
                <v-icon large color="grey--text text--darken-4">mdi-magnify-close</v-icon>
                <span class="grey--text text--darken-2 text-h5 font-weight-bold">Sem Resultados!</span>
            </div>

            <modal-template :title="currentModalTitle" :maxWidth="currentModalWidth">
                <modal v-if="this.currentModalTitle == 'Adicionar Empréstimo'" :books="availableBooks" :students="availableStudents"/>
                <modal v-if="this.currentModalTitle == 'Deletar Empréstimo' || this.currentModalTitle == 'Realizar Devolução?'" 
                :loan="selectedLoan"/>
            </modal-template>
            
        </div>`
}