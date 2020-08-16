import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddBookModal',
    data: () => ({
        valid: false,
        book: {
            title: '',
            pages: '',
            gender: 'Romance',
            author: 'Tiririca'
        },
        authors: [
            'Tiririca',
            'Tiringa',
            'Abacate',
        ],
        genders: [
            'Romance',
            'Terror',
            'Suspense',
        ],
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        $bus.$on('reset-modal-content', () => {
            this.$refs.form.reset()
        })
    },
    beforeDestoy() {
        $bus.$off('reset-modal-content')
    },
    methods: {
        validate () {
            this.$refs.form.validate()
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
                            v-model="book.gender" :items="genders" :rules="[v => !!v || 'Item necessário']" label="Gênero" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-select
                            v-model="book.author" :items="authors" :rules="[v => !!v || 'Item necessário']" label="Autor" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row>     
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="validate">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
