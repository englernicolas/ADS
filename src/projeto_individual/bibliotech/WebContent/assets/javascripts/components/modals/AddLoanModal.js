import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddLoanModal',
    props: {
        books: Array,
        students: Array
    },
    data: vm => ({
        creating: false,
        valid: false,
        loan: {},
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        menu: false,
        date: new Date().toISOString().substr(0, 10),
        deliveryDate: null,
        dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
        deliveryDateFormatted: null
    }),
    mounted() {
        this.updateDeliveryDate
        $bus.$on('load-content', () => {
            this.loan = {
                loanDate: new Date().toISOString().substr(0,10),
                deliveryDate: '',
            }
            this.updateDeliveryDate
        })
    },
    watch: {
        date (val) {
            this.dateFormatted = this.formatDate(this.date)
            this.updateDeliveryDate
        },
    },
    computed: {
        updateDeliveryDate () {
            if (this.date)
            this.deliveryDateFormatted = this.formatDate(this.addDays(this.date, 15))
        },
        computedDateFormatted () {
            return this.formatDate(this.date)
        },
    },
    methods: {
        addDays(date, days) {
            const copy = new Date(date)
            copy.setDate(copy.getDate() + days)
            return copy.toISOString().substr(0,10)
        },
        validate () {
            this.$refs.form.validate()
        },
        limiter(e) {
            if(e.length > 2) {
                e.pop()
            }
        },
        formatDate (date) {
            if (!date) return null

            const [year, month, day] = date.split('-')
            return `${day}/${month}/${year}`
        },
        parseDate (date) {
            if (!date) return null

            const [day, month, year] = date.split('/')
            return `${year}-${month.padStart(2, '0')}-${day.padStart(2, '0')}`
        },
        async createLoan() {
            this.validate()

            if (this.valid) {
                this.creating = true

                this.loan.loanDate = this.date
                this.loan.deliveryDate = this.deliveryDate

                const loan = this.loan

                await axios.post('/loan/create', loan)
                    .then(() => {
                        $bus.$emit('refresh-loans')
                        $bus.$emit('refresh-availableStudents')
                        $bus.$emit('refresh-availableBooks')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar empréstimo"
                    })
                    .finally(() => {
                        this.creating = false
                    })
            }
        },
    },
    template: /*html*/ `
    <div v-if="students.length > 0 && books.length > 0">
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-select
                            v-model="loan.studentId" :items="students" :rules="requiredMessage"
                            :item-text="item => item.firstName + ' ' + item.lastName" item-value="id" 
                            label="Estudante" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>    
                        <v-select
                            v-model="loan.booksIds" :items="books" :rules="requiredMessage" v-on:input="limiter"
                            item-text="title" item-value="id" label="Livros" color="teal"
                            hint="Escolha no máximo 2" persistent-hint required outlined multiple
                        ></v-select>
                    </v-col>
                </v-row> 
                <v-row>
                    <v-col>
                        <v-menu
                          ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                              v-model="dateFormatted"
                              label="Data de Entrega"
                              persistent-hint
                              v-bind="attrs"
                              @blur="date = parseDate(dateFormatted)"
                              v-on="on"
                              outlined    
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="date"
                            no-title
                            @input="menu = false"
                          ></v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>
                        <v-text-field
                            v-model="deliveryDateFormatted" :input="deliveryDate = parseDate(deliveryDateFormatted)" :rules="requiredMessage" label="Entrega" color="teal" disabled outlined
                        ></v-text-field>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createLoan" v-if="!creating">
                            Adicionar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>
    <div v-else class="text-center">
        <v-icon large color="grey--text text--darken-4">mdi-information</v-icon>
        <span class="grey--text text--darken-2 text-h6 font-weight-bold">Certifique-se que existem Alunos e Livros para poder criar um Empréstimo</span>
    </div>`
}