import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddGenreModal',
    data: () => ({
        valid: false,
        creating: false,
        genre: {
            name: '',
        },
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.genre = {
                    name: '',
                }
            })   
        }
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async createGenre() {
            this.validate()

            if (this.valid) {
                this.creating = true

                const genre = this.genre

                await axios.post('/genre/create', genre)
                    .then(() => {
                        $bus.$emit('refresh-genres')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar gênero"
                    })
                    .finally(() => {
                        this.creating = false
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
                            v-model="genre.name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createGenre" v-if="!creating">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
