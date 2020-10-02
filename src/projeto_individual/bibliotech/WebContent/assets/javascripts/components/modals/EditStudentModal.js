import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'EditStudentModal',
    props: {
        currentStudent: Object,
        schools: Array,
        genders: Array
    },
    data: () => ({
        students: {},
        student: {},
        valid: true,
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
            this.student = this.currentStudent
        })
    },
    methods: {
        validate () {
            this.$refs.form.validate()
            $bus.$off('reset-content')
        },

        async getStudents() {
            this.loadingUsers = true

            await axios.get('/student/list')
                .then((response) => {
                    this.students = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por estudantes"
                })
                .finally(() => {
                    this.loadingUsers = false
                })
        },

        async editStudent() {
            this.validate()

            if (this.valid) {
                this.editing = true

                const student = this.student

                await axios.put('/student/edit', student)
                    .then(() => {
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
                            v-model="student.password" :rules="requiredMessage" label="Senha" color="teal" required outlined
                        ></v-text-field>
                    </v-col>
                </v-row>
                <v-row>
                    <v-col>
                        <v-text-field v-mask="['##/##/####']"
                            v-model="student.birthDate" :rules="requiredMessage" label="Data de Nascimento" color="teal" outlined required
                        ></v-text-field>
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
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="editStudent">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
