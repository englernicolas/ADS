import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'DeleteLoanModal',
    props: {
        loan: Object
    },
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
        getLoanId() {
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

        async deleteLoan() {
            this.validate()

            if (this.valid) {
                this.deleting = true

                this.deleteInfo.booksIds = this.loan.booksIds
                this.deleteInfo.studentId = this.loan.studentId
                this.deleteInfo.id = this.getLoanId

                const deleteInfo = this.deleteInfo

                await axios.put('/loan/deleteDeliverAndPay', deleteInfo)
                    .then(() => {
                        $bus.$emit('refresh-availableStudents')
                        $bus.$emit('refresh-availableBooks')
                        $bus.$emit('refresh-loans')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar deletar livro"
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
                        <v-btn :disabled="!valid" color="error" class="white--text text-lg-right" @click="deleteLoan">
                            Deletar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
