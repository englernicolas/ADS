import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import EditPasswordModal from './modals/EditPasswordModal.js'

export default {
    name: 'EditProfile',
    data: () => ({
        valid: true,
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        emailRules: [
            v => /.+@.+\..+/.test(v) || 'Email inválido',
        ],
        cpfRules: [
            v => /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/.test(v) || 'CPF inválido',
        ],
        genders: [],
        student: {},
        menu: false,
        loadingUser: false,
        editing: false,
        dateFormatted: ""
    }),
    mounted() {
        this.getGenders()
        this.getSchools()
        this.getStudent()
        this.$options.components.Modal = EditPasswordModal
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
        openModal() {
            $bus.$emit('open-modal')
        },
        formatDate (date) {
            if (!date) return null

            const [year, month, day] = date.split('-')
            return `${day}/${month}/${year}`
        },
        async getSchools() {
            this.loadingSchools = true

            await axios.get('/school/list')
                .then((response) => {
                    this.schools = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por escolas"
                })
                .finally(() => {
                    this.loadingSchools = false
                })
        },
        async getGenders() {
            this.loadingGenders = true

            await axios.get('/gender/list')
                .then((response) => {
                    this.genders = response.data;
                })
                .catch(() => {
                    this.error = "Ocorreu um erro ao tentar buscar por gêneros"
                })
                .finally(() => {
                    this.loadingGenders = false
                })
        },
        async getStudent() {
            this.loadingUser = true

            await axios.get('/student/getById?id=' + auth.user.id)
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

                const student = this.student

                await axios.put('/student/edit', student)
                    .then(() => {
                        window.location.reload()
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
    template: /*html*/ `<div>
                    <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Editar Perfil</span></div>
                                        
                    <v-divider></v-divider>
                    
                    <v-form class="mx-16 my-5" ref="form" v-model="valid">
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
                                    <v-text-field v-mask="['###.###.###-##']"
                                        v-model="student.cpf" :rules="requiredMessage && cpfRules"
                                        label="CPF" color="teal" outlined disabled
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>    
                                    <v-select
                                        v-model="student.genderId" :items="genders" item-text="name" item-value="id"
                                        :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" disabled outlined
                                    ></v-select>
                                </v-col>
                                <v-col>
                                    <v-text-field v-model="dateFormatted" label="Data de Nascimento" disabled outlined></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-select
                                    v-model="student.schoolId" class="px-3" :items="schools" item-text="name" item-value="id"
                                    :rules="[v => !!v || 'Item necessário']" label="Escola" color="teal" disabled outlined
                                ></v-select>
                            </v-row>     
                            
                            <v-row>
                                <v-col class="text-right">    
                                    <v-btn color="secondary" class="white--text text-lg-right" @click="openModal">
                                        Alterar Senha
                                    </v-btn>
                                </v-col>
                                <v-col class="text-left">
                                    <v-btn :disabled="!valid || loadingUser == true || editing == true" color="primary" class="white--text text-lg-right" @click="editStudent">
                                        Salvar
                                    </v-btn>
                                </v-col>
                            </v-row>
                        </v-container>
                    </v-form>

                    <modal-template title="Editar Senha">
                        <modal/>
                    </modal-template>
                    
               </div>`
}
