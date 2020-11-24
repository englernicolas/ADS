import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddStudentModal',
    props: {
        schools: Array,
        genders: Array
    },
    data: () => ({
        creating: false,
        valid: false,
        student: {},
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
    }),
    mounted() {
        $bus.$on('load-content', () => {
            this.student = {
                genderId: 1,
                schoolId: 1
            }
        })
    },
    watch: {
        menu (val) {
            val && setTimeout(() => (this.$refs.picker.activePicker = 'YEAR'))
        },
    },
    methods: {
        save (date) {
            this.$refs.menu.save(date)
        },

        validate () {
            this.$refs.form.validate()
        },

        async createStudent() {
            this.validate()

            if (this.valid) {
                this.creating = true

                const student = this.student

                await axios.post('/student/create', student)
                    .then(() => {
                        $bus.$emit('refresh-students')
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar estudante"
                    })
                    .finally(() => {
                        this.creating = false
                    })
            }
        },
    },
    template: /*html*/ `
    <div v-if="schools.length > 0">
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
                                    v-model="student.birthDate"
                                    label="Data de Nascimento"
                                    readonly
                                    hint="YYYY-MM-DD format"
                                        persistent-hint
                                    v-bind="attrs"
                                    v-on="on"
                                    outlined
                                ></v-text-field>
                            </template>
                            <v-date-picker
                                ref="picker"
                                v-model="student.birthDate"
                                :max="new Date().toISOString().substr(0, 10)"
                                min="1950-01-01"
                                @change="save"
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
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createStudent" v-if="!creating">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>
    <div v-else class="text-center">
        <v-icon large color="grey--text text--darken-4">mdi-information</v-icon>
        <span class="grey--text text--darken-2 text-h6 font-weight-bold">Certifique-se que existem Escolas para poder criar um Estudante</span>
    </div>    
    `
}
