import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddBookModal',
    props: {
        authors: Array,
        genres: Array
    },
    data: () => ({
        creating: false,
        valid: false,
        dialogAuthor: false,
        dialogGenre: false,
        book: {
            genreId: 1,
            authorId: 1
        },
        genre: {},
        author: {},
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        $bus.$on('load-content', () => {
            this.book = {
                genreId: 1,
                authorId: 1
            }
        })
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async createBook() {
            this.validate()

            if (this.valid) {
                this.creating = true

                const book = this.book

                await axios.post('/book/create', book)
                    .then(() => {
                        $bus.$emit('refresh-books')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar livro"
                    })
                    .finally(() => {
                        this.creating = false
                    })
            }
        },
        createGenre() {
            this.creating = true

            const genre = this.genre

            axios.post('/genre/create', genre)
                .then(() => {
                    $bus.$emit('refresh-genres')
                    this.genre = {}
                    this.dialogGenre = false
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar criar gênero"
                })
                .finally(() => {
                    this.creating = false
                })
        },
        createAuthor() {
            this.creating = true

            const author = this.author

            axios.post('/author/create', author)
                .then(() => {
                    $bus.$emit('refresh-authors')
                    this.author = {}
                    this.dialogAuthor = false
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar criar autor"
                })
                .finally(() => {
                    this.creating = false
                })
        },
        closeAuthorDialog() {
            this.author = {}
            this.dialogAuthor = false
        },
        closeGenreDialog() {
            this.genre = {}
            this.dialogGenre = false
        },
    },
    template: /*html*/ `
    <div v-if="genres.length > 0 && authors.length > 0">
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="book.title" :rules="requiredMessage" label="Título" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            type="number" v-model="book.pages" :rules="requiredMessage" label="Número de páginas" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>    
                        <v-select
                            v-model="book.genreId" :items="genres" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Gênero" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-dialog
                            v-model="dialogGenre"
                            persistent
                            max-width="290"
                        >
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn class="ml-5 py-6" color="secondary" v-on="on" v-bind="attrs">
                                Adicionar Gênero
                                <v-icon right dark>mdi-plus</v-icon>
                            </v-btn>
                        </template>
                            <v-card>
                                <v-form>
                                    <v-card-title class="headline">
                                        Adicionar Gênero
                                    </v-card-title>
                                    <v-card-text>
                                        <v-text-field dense v-model="genre.name" :rules="requiredMessage" 
                                            label="Nome" color="teal" required outlined
                                        ></v-text-field>
                                    </v-card-text>
                                    <v-card-actions class="justify-center">
                                        <v-btn color="primary" class="white--text text-lg-right" @click="createGenre" v-if="!creating">
                                            Salvar
                                        </v-btn>
                                        <v-btn color="red" class="white--text text-lg-right" @click="closeGenreDialog">
                                            Fechar
                                        </v-btn>
                                    </v-card-actions>
                                </v-form>
                            </v-card>
                        </v-dialog>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-select
                            v-model="book.authorId" :items="authors" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Autor" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-dialog
                            v-model="dialogAuthor"
                            persistent
                            max-width="290"
                        >
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn class="ml-5 py-6" color="secondary" v-on="on" v-bind="attrs">
                                <span class="mr-3">Adicionar Autor</span>
                                <v-icon right dark>mdi-plus</v-icon>
                            </v-btn>
                        </template>
                            <v-card>
                                <v-form>
                                    <v-card-title class="headline">
                                        Adicionar Autor
                                    </v-card-title>
                                    <v-card-text>
                                        <v-text-field dense v-model="author.name" :rules="requiredMessage" 
                                            label="Nome" color="teal" required outlined
                                        ></v-text-field>
                                    </v-card-text>
                                    <v-card-actions class="justify-center">
                                        <v-btn color="primary" class="white--text text-lg-right" @click="createAuthor" v-if="!creating">
                                            Salvar
                                        </v-btn>
                                        <v-btn color="red" class="white--text text-lg-right" @click="closeAuthorDialog">
                                            Fechar
                                        </v-btn>
                                    </v-card-actions>
                                </v-form>
                            </v-card>
                        </v-dialog>
                    </v-col>
                </v-row>     
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createBook" v-if="!creating">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>
    <div v-else class="text-center">
        <v-icon large color="grey--text text--darken-4">mdi-information</v-icon>
        <span class="grey--text text--darken-2 text-h6 font-weight-bold">Certifique-se que existem Autores e Gêneros para poder criar um Livro</span>
    </div>`
}
