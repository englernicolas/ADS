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
        book: {
            genreId: 1,
            authorId: 1
        },
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
                            v-model="book.pages" :rules="requiredMessage" label="Número de páginas" color="teal" required outlined
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
                        <v-select
                            v-model="book.authorId" :items="authors" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Autor" color="teal" required outlined
                        ></v-select>
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
        <span class="grey--text text--darken-3 text-h6 font-weight-bold">Certifique-se que existem Autores e Generos para poder criar um livro</span>
    </div>`
}
