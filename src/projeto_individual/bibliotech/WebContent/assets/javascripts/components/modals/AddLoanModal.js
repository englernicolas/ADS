import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddLoanModal',
    data: () => ({
        valid: false,
        loan: {
            loanDate: new Date().toISOString().substr(0,10),
            deliveryDate: '',
            book: 'Romance',
            student: 'Tiririca'
        },
        students: [
            'Tiririca',
            'Tiringa',
            'Abacate',
        ],
        books: [
            'Romance',
            'Terror',
            'Suspense',
        ],
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        menu: false
    }),
    mounted() {
        $bus.$on('reset-modal-content', () => {
            this.$refs.form.reset()
        })
    },
    beforeDestoy() {
        $bus.$off('reset-modal-content')
    },
    computed: {
        updateDeliveryDate () {
            if (this.loan.loanDate)
            this.loan.deliveryDate = this.addDays(this.loan.loanDate, 15)
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
    },
    template: /*html*/ `
    <div>
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-select
                            v-model="loan.student" :items="students" :rules="[v => !!v || 'Item necessário']" label="Aluno" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>    
                        <v-select
                            v-model="loan.book" :items="books" :rules="[v => !!v || 'Item necessário']" label="Livro" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row> 
                <v-row>
                    <v-col>
                        <v-menu
                            ref="menu"
                            v-model="menu"
                            :close-on-content-click="false"
                            :return-value.sync="loan.loanDate"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                        >
                            <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                                v-model="loan.loanDate"
                                label="Data"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                                color="teal"
                                outlined
                            ></v-text-field>
                            </template>
                            <v-date-picker v-model="loan.loanDate" color="teal" :change="updateDeliveryDate" no-title scrollable>
                            <v-spacer></v-spacer>
                            <v-btn text color="primary" @click="menu = false">Cancel</v-btn>
                            <v-btn text color="primary" @click="$refs.menu.save(loan.loanDate)">OK</v-btn>
                            </v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>
                        <v-text-field
                            v-model="loan.deliveryDate" :rules="requiredMessage" label="Entrega" color="teal" disabled outlined
                        ></v-text-field>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="validate">
                            Adicionar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}