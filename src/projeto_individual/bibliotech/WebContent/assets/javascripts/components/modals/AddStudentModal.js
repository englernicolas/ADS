import { $bus } from '../../utils/eventBus.js'

export default {
    name: 'AddStudentModal',
    data: () => ({
        editando: false,
        valid: false,
        student: {
            firstName: '',
            lastName: '',
            password: '',
            email: '',
            birthDate: '',
            cpf: '',
            genderId: 1,
            schoolId: 1
        },
        schools: [
            {name: 'ESCOLA CABEÇA DE GELO', id: 1},
            {name: 'SENAI', id: 2},
            {name: 'CARROSSEL', id: 3},
        ],
        genders: [
            {name: 'Indefinido', id: 1},
            {name: 'Masculino', id: 2},
            {name: 'Feminino', id: 3},
        ],
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
        if(this.$refs.form) {
            $bus.$on('reset-modal-content', () => {
                this.$refs.form.reset()
            })   
        }
    },
    beforeDestoy() {
        $bus.$off('reset-modal-content')
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
                this.editando = true

                this.student.cpf = this.student.cpf.replace("-","").replace(/\./g,"")

                const student = this.student

                await axios.post('/student/create', student)
                    .then(() => {
                        $bus.$emit('close-modal')
                    })
                    .catch(() => {
                        this.error = "Ocorreu um erro ao tentar criar estudante"
                    })
                    .finally(() => {
                        this.editando = false
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
                                label="Data de nascimento"
                                readonly
                                v-bind="attrs"
                                v-on="on"
                                color="teal"
                                outlined
                            ></v-text-field>
                            </template>
                            <v-date-picker
                            ref="picker"
                            v-model="student.birthDate"
                            :max="new Date().toISOString().substr(0, 10)"
                            min="1950-01-01"
                            @change="save"
                            color="primary"
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
                            v-model="student.genderId" :items="genders" item-value="id" item-text="name" :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" required outlined
                        ></v-select>
                    </v-col>
                    <v-col>
                        <v-select
                            v-model="student.schoolId" :items="schools" item-value="id" item-text="name" :rules="[v => !!v || 'Item necessário']" label="Escola" color="teal" required outlined
                        ></v-select>
                    </v-col>
                </v-row>    
                
                <v-row>
                    <v-col class="text-center">
                        <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="createStudent" v-if="!editando">
                            Salvar
                        </v-btn>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </div>`
}
