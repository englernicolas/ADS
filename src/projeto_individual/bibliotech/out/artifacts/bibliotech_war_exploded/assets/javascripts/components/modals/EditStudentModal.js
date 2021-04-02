import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'EditStudentModal',
    props: {
        schools: Array,
        genders: Array
    },
    data: vm => ({
        student: {},
        valid: true,
        loadingUser: false,
        emailRules: [
            v => /.+@.+\..+/.test(v) || 'Email inválido',
        ],
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        cpfRules: [
            v => /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/.test(v) || 'CPF inválido',
        ],
        editing: false,
        menu: false,
        date: null,
        dateFormatted: null,
    }),
    computed: {
        getUserId() {
            return this.$route.query.id
        }
    },
    mounted() {
        this.getStudent()
        $bus.$on('load-content', () => {
            this.getStudent()
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
            $bus.$off('reset-content')
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

        async getStudent() {
            this.loadingUser = true

            await axios.get(`/student/getById?id=${this.getUserId}`)
                .then((response) => {
                    this.student = response.data
                    this.dateFormatted = this.formatDate(this.student.birthDate)
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por estudantes"
                })
                .finally(() => {
                    this.loadingUser = false
                })
        },

        async editStudent() {
            this.validate()

            if (this.valid) {
                this.editing = true

                this.student.password = btoa(this.student.password)
                const student = this.student

                await axios.put('/student/edit', student)
                    .then(() => {
                        $bus.$emit('refresh-students')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar editar estudante"
                    })
                    .finally(() => {
                        this.editing = false
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
                            v-model="student.firstName" :rules="requiredMessage" label="Nome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            v-model="student.lastName" :rules="requiredMessage" label="Sobrenome" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field
                            v-model="student.email" :rules="emailRules" label="E-mail" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                    <v-col>
                        <v-text-field
                            type="password" v-model="student.password" :rules="requiredMessage" label="Senha" color="teal" required outlined
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
                            v-model="student.cpf" :rules="requiredMessage && cpfRules" label="CPF" color="teal" outlined required
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>    
                        <v-select
                            v-model="student.genderId" :items="genders" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-select
                            v-model="student.schoolId" :items="schools" item-text="name" item-value="id"
                            :rules="[v => !!v || 'Item necessário']" label="Escola" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="editStudent" v-if="!editing">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
