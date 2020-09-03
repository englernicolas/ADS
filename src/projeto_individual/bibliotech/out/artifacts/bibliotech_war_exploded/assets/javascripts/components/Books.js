import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

import SearchBox from './utils/SearchBox.js'
Vue.component('SearchBox', SearchBox)

/* MODAIS */
import AddBookModal from './modals/AddBookModal.js'
import AddAuthorModal from './modals/AddAuthorModal.js'
import AddGenreModal from './modals/AddGenreModal.js'
import EditBookModal from './modals/EditBookModal.js'
import DeleteBookModal from './modals/DeleteBookModal.js'

export default {
    name: 'Books',
    data: () => ({
        books: [
            {
                id: 1,
                title: "O casamento",
                author: "Nicholas Sparks",
                gender: "Romance",
                pages: "152",
                loanId: '1',
            },
            {
                id: 2,
                title: "O casamento",
                author: "Nicholas Sparks",
                gender: "Romance",
                pages: "152",
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
                this.currentModalTitle = 'Adicionar Livro'
                this.currentModalWidth = '800'
                this.currentModal(AddBookModal)
            }
            if (modalType == 'addAuthor') {
                this.currentModalTitle = 'Adicionar Autor'
                this.currentModal(AddAuthorModal)
                this.currentModalWidth = '500'
            }
            if (modalType == 'addGenre') {
                this.currentModalTitle = 'Adicionar Gênero'
                this.currentModal(AddGenreModal)
                this.currentModalWidth = '500'
            }
            if (modalType == 'edit') {
                this.currentModalTitle = 'Editar Livro'
                this.currentModal(EditBookModal)
                this.currentModalWidth = '800'
                this.$router.push({ path: '/books', query: { id: id } })
            }
            if (modalType == 'delete') {
                this.currentModalTitle = 'Deletar Livro'
                this.currentModal(DeleteBookModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/books', query: { id: id } })
            }
            
            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
        }
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Livros</span></div>
                                
            <v-divider></v-divider>
            
            <div class="d-flex mt-5">
                <v-btn class="ml-16" color="secondary" @click="openModal('addBook')">
                    Adicionar
                    <v-icon right dark>mdi-book-open-page-variant</v-icon>
                </v-btn>
                <v-btn class="ml-5" color="secondary" @click="openModal('addAuthor')">
                    Adicionar
                    <v-icon right dark>mdi-account-plus</v-icon>
                </v-btn>
                <v-btn class="ml-5" color="secondary" @click="openModal('addGenre')">
                    Adicionar
                    <v-icon right dark>mdi-gender-male-female</v-icon>
                </v-btn>

                <v-spacer></v-spacer>

                <search-box class="mr-16"></search-box>
            </div>

            <v-card v-for="book in books" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Título:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.title}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Autor:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.author}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.gender}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Núm. Páginas:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.pages}}</span>
                            </v-row>
                        </v-col>
                        <v-col v-if="book.loanId">
                            <v-row>
                                <span class="font-weight-bold">Cód. Empreśtimo:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.loanId}}</span>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('edit', book.id)" class="teal--text d-block">
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn icon @click="openModal('delete', book.id)" class="red--text d-block">
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