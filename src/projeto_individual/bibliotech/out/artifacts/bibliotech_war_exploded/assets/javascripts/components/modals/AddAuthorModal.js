import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddAuthorModal',
    data: () => ({
        valid: false,
        creating: false,
        author: {
            name: '',
        },
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    mounted() {
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.author = {
                    name: '',
                }
            })   
        }
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async createAuthor() {
            this.validate()

            if (this.valid) {
                this.creating = true

                const author = this.author

                await axios.post('/author/create', author)
                    .then(() => {
                        $bus.$emit('refresh-authors')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar autor"
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
                            v-model="author.name" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createAuthor" v-if="!creating">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
