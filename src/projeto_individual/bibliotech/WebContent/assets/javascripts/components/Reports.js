export default {
    name: 'Reports',
    data: () => ({
        menuInitialDate: false,
        menuEndDate: false,
        initialDate: null,
        initialDateFormatted: null,
        endDate: null,
        endDateFormatted: null,
        reports: [
            {value:"mostBorrowedBooks", name:"Livros mais emprestados"},
            {value:"studentsThatMostLent", name:"Alunos que mais emprestam livros"}
        ],
        selectedReport: "mostBorrowedBooks"
    }),
    watch: {
        initialDate (val) {
            this.initialDateFormatted = this.formatDate(this.initialDate)
        },
        endDate (val) {
            this.endDateFormatted = this.formatDate(this.endDate)
        },
    },
    methods: {
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
        generateReport() {
            this.loadingLoans = true

            let url = '/report/' + this.selectedReport

            if (this.initialDate!=null || this.endDate!=null) {
                url += '?'
                if (this.initialDate!=null) {
                    url += 'initialDate=' + this.initialDate
                } else if (this.endDate!=null) {
                    url += 'endDate=' + this.endDate
                }
            }

            axios.get(url)
                .then((response) => {
                    alert(response.data)
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar gerar relatorio"
                })
                .finally(() => {
                    this.loadingLoans = false
                })
        },
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Relatórios</span></div>
                                
            <v-divider></v-divider>

            <div class="mx-16 my-5">
                <v-row>
                    <v-col>
                        <v-select
                            v-model="selectedReport" :items="reports" item-text="name" item-value="value"
                            label="Tipo" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-menu
                          ref="menuInitialDate"
                            v-model="menuInitialDate"
                            :close-on-content-click="false"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                              v-model="initialDateFormatted"
                              label="Data Inicial"
                              persistent-hint
                              v-bind="attrs"
                              @blur="initialDate = parseDate(initialDateFormatted)"
                              v-on="on"
                              autocomplete="off"
                              outlined    
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="initialDate"
                            no-title
                            @input="menuInitialDate = false"
                          ></v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>
                        <v-menu
                          ref="menuEndDate"
                            v-model="menuEndDate"
                            :close-on-content-click="false"
                            transition="scale-transition"
                            offset-y
                            min-width="290px"
                        >
                          <template v-slot:activator="{ on, attrs }">
                            <v-text-field
                              v-model="endDateFormatted"
                              label="Data Final"
                              persistent-hint
                              v-bind="attrs"
                              @blur="endDate = parseDate(endDateFormatted)"
                              v-on="on"
                              autocomplete="off"
                              outlined    
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="endDate"
                            no-title
                            @input="menuEndDate = false"
                          ></v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>
                        <v-btn class="ml-5 mb-2" color="primary" @click="generateReport">Gerar</v-btn>
                    </v-col>
                </v-row>
            </div>
            <v-divider></v-divider>
        </div>`
}