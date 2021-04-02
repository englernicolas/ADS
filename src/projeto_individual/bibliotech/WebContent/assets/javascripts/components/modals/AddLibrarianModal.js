import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddLibrarianModal',
    props: {
        schools: Array,
        genders: Array
    },
    data: vm => ({
        creating: false,
        valid: false,
        dialog: false,
        librarian: {
            genderId: 1,
            schoolId: 1
        },
        school:{},
        emailRules: [
            v => /.+@.+\..+/.test(v) || 'Email inválido',
        ],
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        cpfRules: [
            v => /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/.test(v) || 'CPF inválido',
        ],
        menu: false,
        date: new Date().toISOString().substr(0, 10),
        dateFormatted: vm.formatDate(new Date().toISOString().substr(0, 10)),
    }),
    mounted() {
        $bus.$on('load-content', () => {
            this.librarian = {
                genderId: 1,
                schoolId: 1
            }
        })
    },
    watch: {
        date (val) {
            this.dateFormatted = this.formatDate(this.date)
        },
    },
    methods: {
        save (date) {
            this.$refs.menu.save(date)
        },
        
        validate () {
            this.$refs.form.validate()
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

        async createLibrarian() {
            this.validate()

            if (this.valid) {
                this.creating = true

                this.librarian.birthDate = this.date
                this.librarian.password = btoa(this.librarian.password)
                const librarian = this.librarian

                await axios.post('/librarian/create', librarian)
                    .then(() => {
                        $bus.$emit('refresh-librarians')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar bibliotecário"
                    })
                    .finally(() => {
                        this.creating = false
                    })
            }
        },
        async createSchool() {
            this.creating = true

            const school = this.school

            await axios.post('/school/create', school)
                .then(() => {
                    $bus.$emit('refresh-schools')
                    this.school = {}
                    this.dialog = false
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar criar escola"
                })
                .finally(() => {
                    this.creating = false
                })
        },
    },
    template: /*html*/ `
    <div v-if="schools.length > 0">
        <v-form class="mx-6" ref="form" v-model="valid">
            <v-container>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="librarian.firstName" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            v-model="librarian.lastName" :rules="requiredMessage" label="Sobrenome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="librarian.email" :rules="emailRules" label="E-mail" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            type="password" v-model="librarian.password" :rules="requiredMessage" label="Senha" color="teal" required outlined
                        ></v-text-field>
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
                              label="Data de Nascimento"
                              persistent-hint
                              v-bind="attrs"
                              @blur="date = parseDate(dateFormatted)"
                              v-on="on"
                              outlined    
                            ></v-text-field>
                          </template>
                          <v-date-picker
                            v-model="date"
                            :max="new Date().toISOString().substr(0, 10)"
                            no-title
                            @input="menu = false"
                          ></v-date-picker>
                        </v-menu>
                    </v-col>
                    <v-col>    
                        <v-text-field v-mask="['###.###.###-##']"
                            v-model="librarian.cpf" :rules="requiredMessage && cpfRules" label="CPF" color="teal" outlined required
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>    
                        <v-select
                            v-model="librarian.genderId" :items="genders" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-select
                            v-model="librarian.schoolId" :items="schools" item-text="name" item-value="id" 
                            :rules="[v => !!v || 'Item necessário']" label="Escola" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-dialog
                            v-model="dialog"
                            persistent
                            max-width="290"
                        >
                        <template v-slot:activator="{ on, attrs }">
                            <v-btn class="ml-5 py-6" color="secondary" v-on="on" v-bind="attrs">
                                Adicionar Escola
                                <v-icon right dark>mdi-plus</v-icon>
                            </v-btn>
                        </template>
                            <v-card>
                                <v-form>
                                    <v-card-title class="headline">
                                        Adicionar Escola
                                    </v-card-title>
                                    <v-card-text>
                                        <v-text-field dense v-model="school.name" :rules="requiredMessage" 
                                            label="Nome" color="teal" required outlined
                                        ></v-text-field>
                                    </v-card-text>
                                    <v-card-actions class="justify-center">
                                        <v-btn color="primary" class="white--text text-lg-right" @click="createSchool" v-if="!creating">
                                            Salvar
                                        </v-btn>
                                        <v-btn color="red" class="white--text text-lg-right" @click="dialog = false; school.name = ''">
                                            Fechar
                                        </v-btn>
                                    </v-card-actions>
                                </v-form>
                            </v-card>
                        </v-dialog>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createLibrarian" v-if="!creating">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>
    <div v-else class="text-center">
        <v-icon large color="grey--text text--darken-4">mdi-information</v-icon>
        <span class="grey--text text--darken-2 text-h6 font-weight-bold">Certifique-se que existem Escolas para poder criar um Bibliotecário</span>
    </div>  
    `
}
