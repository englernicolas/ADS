import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'DeleteLibrarianModal',
    data: () => ({
        valid: false,
        deleteInfo: {
            id: '',
            deletedReason: '',
        },
        deleting: false,
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
    }),
    computed: {
        getUserId() {
            return this.$route.query.id
        }
    },
    mounted() {
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.$refs.form.reset()
            })
        }
    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },

        async deleteLibrarian() {
            this.validate()

            if (this.valid) {
                this.deleting = true

                this.deleteInfo.id = this.getUserId

                const deleteInfo = this.deleteInfo

                await axios.put('/librarian/delete', deleteInfo)
                    .then(() => {
                        $bus.$emit('refresh-librarians')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar deletar bibliotecário"
                    })
                    .finally(() => {
                        this.deleting = false
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
                        <v-textarea
                            v-model="deleteInfo.deletedReason" :rules="requiredMessage" color="teal" placeholder="Seu motivo aqui" required outlined
                        ></v-textarea>
                    </v-col>
                </v-row> 
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="error" class="white--text text-lg-right" @click="deleteLibrarian">
                            Deletar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
