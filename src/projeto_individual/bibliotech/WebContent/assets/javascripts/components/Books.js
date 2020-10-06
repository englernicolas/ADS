import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import AddBookModal from './modals/AddBookModal.js'
import AddAuthorModal from './modals/AddAuthorModal.js'
import AddGenreModal from './modals/AddGenreModal.js'
import EditBookModal from './modals/EditBookModal.js'
import DeleteBookModal from './modals/DeleteBookModal.js'

export default {
    name: 'Books',
    data: () => ({
        books: [],
        currentModalTitle: '',
        currentModalWidth: '',
        bookId: '',
        loadingBooks: false,
        loadingAuthors: false,
        loadingGenres: false,
        authors: [],
        genres: [],
        searchText: ''
    }),
    mounted() {
        this.getBooks();
        this.getAuthors();
        this.getGenres();
        $bus.$on('refresh-books', () => {
            this.getBooks();
        })
        $bus.$on('refresh-authors', () => {
            this.getAuthors();
        })
        $bus.$on('refresh-genres', () => {
            this.getGenres();
        })
    },
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
        },
        async getBooks() {
            this.loadingBooks = true

            await axios.get('/book/list')
                .then((response) => {
                    this.books = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por livro"
                })
                .finally(() => {
                    this.loadingBooks = false
                })
        },
        async getAuthors() {
            this.loadingAuthors = true

            await axios.get('/author/list')
                .then((response) => {
                    this.authors = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por autores"
                })
                .finally(() => {
                    this.loadingAuthors = false
                })
        },
        async getGenres() {
            this.loadingGenres = true

            await axios.get('/genre/list')
                .then((response) => {
                    this.genres = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por gêneros"
                })
                .finally(() => {
                    this.loadingGenres = false
                })
        },
        async getSearchBooks() {
            this.loadingUsers = true

            await axios.get(`/book/getBySearch?searchText=${this.searchText}`)
                .then((response) => {
                    this.books = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por livros"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },
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

                <div class="d-flex mr-16">
                    <v-text-field v-model="searchText" color="teal" placeholder="Pesquisar..." hide-details dense filled clearable></v-text-field>
                    <v-btn @click="getSearchBooks" color="primary"><v-icon color="white">mdi-magnify</v-icon></v-btn>          
                </div>
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
                                <span>{{authors.find(it => it.id == book.authorId)?.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{genres.find(it => it.id == book.genreId)?.name}}</span>
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
                        <!--
                        <v-col v-if="book.loanId">
                            <v-row>
                                <span class="font-weight-bold">Cód. Empreśtimo:</span>
                            </v-row>
                            <v-row>
                                <span>{{book.loanId}}</span>
                            </v-row>
                        </v-col>
                        -->
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

            <div v-if="books.length == 0 " class="mt-16 text-center">
                <v-icon large color="grey--text text--darken-4">mdi-magnify-close</v-icon>
                <span class="grey--text text--darken-2 text-h5 font-weight-bold">Sem Resultados!</span>
            </div>

            <modal-template :title="currentModalTitle" :maxWidth="currentModalWidth">
                <modal v-if="this.currentModalTitle == 'Deletar Livro' || this.currentModalTitle == 'Adicionar Autor' || this.currentModalTitle == 'Adicionar Gênero'"/>
                <modal v-if="this.currentModalTitle == 'Adicionar Livro'" :authors="authors" :genres="genres"/>
                <modal v-if="this.currentModalTitle == 'Editar Livro'" :authors="authors" :genres="genres"/>
            </modal-template>
            
        </div>`
}