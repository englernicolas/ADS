import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'DeliverLoanModal',
    props: {
        loan: Object
    },
    data: () => ({
        valid: false,
        deliverInfo: {},
        delivering: false,
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

        async deliverLoan() {
            this.validate()

            if (this.valid) {
                this.delivering = true

                this.deliverInfo.booksIds = this.loan.booksIds
                this.deliverInfo.studentId = this.loan.studentId
                this.deliverInfo.id = this.getLoanId

                const deliverInfo = this.deliverInfo

                await axios.put('/loan/deleteAndDeliver', deliverInfo)
                    .then(() => {
                        $bus.$emit('refresh-availableStudents')
                        $bus.$emit('refresh-availableBooks')
                        $bus.$emit('refresh-loans')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar devolver o livro"
                    })
                    .finally(() => {
                        this.delivering = false
                    })
            }
        },
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <span><v-icon class="mb-1">mdi-alert</v-icon> - Deseja mesmo realizar a devolução? Fazendo isso você assume que o estudante devolveu o livro ao acervo.</span>
                </v-row>
                <v-row>
                    <v-col class="text-center">
                        <v-btn color="primary" @click="deliverLoan" class="white--text text-lg-right">
                            Confirmar 
                            <v-icon small class="ml-1">mdi-alert</v-icon>
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
