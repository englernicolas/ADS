import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'EditBookModal',
    props: {
      authors: Array,
      genres: Array
    },
    data: () => ({
        valid: true,
        editing: false,
        book: {},
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    computed: {
        getBookId() {
            return this.$route.query.id
        }
    },
    mounted() {
        this.getBook()
        $bus.$on('load-content', () => {
            this.getBook()
        })
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async getBook() {
            this.loadingUser = true

            await axios.get(`/book/getById?id=${this.getBookId}`)
                .then((response) => {
                    this.book = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por livros"
                })
                .finally(() => {
                    this.loadingUser = false
                })
        },

        async editBook() {
            this.validate()

            if (this.valid) {
                this.editing = true

                const book = this.book

                await axios.put('/book/edit', book)
                    .then(() => {
                        $bus.$emit('refresh-books')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar editar livro"
                    })
                    .finally(() => {
                        this.editing = false
                    })
            }
        },
    },
    template: /*html*/ `
    <div>
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
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="editBook" v-if="!editing">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
