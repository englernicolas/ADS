import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import AddStudentModal from './modals/AddStudentModal.js'
import AddSchoolModal from './modals/AddSchoolModal.js'
import EditStudentModal from './modals/EditStudentModal.js'
import DeleteStudentModal from './modals/DeleteStudentModal.js'
import StudentLoansModal from './modals/StudentLoansModal.js'

export default {
    name: 'Students',
    data: () => ({
        students: [],
        currentModalTitle: '',
        currentModalWidth: '',
        studentId: '',
        loadingUsers: false,
        loadingSchools: false,
        loadingGenders: false,
        schools: [],
        genders: [],
        searchText: ''
    }),
    mounted() {
        this.getStudents();
        this.getSchools();
        this.getGenders();
        $bus.$on('refresh-students', () => {
            this.getStudents();
        })
        $bus.$on('refresh-schools', () => {
            this.getSchools();
        })

    },
    methods: {
        validate () {
            this.$refs.form.validate()
        },
        openModal(modalType, id) {
            if (modalType == 'addUser') {
                this.currentModalTitle = 'Adicionar Estudante'
                this.currentModalWidth = '800'
                this.currentModal(AddStudentModal)
            }
            if (modalType == 'addSchool') {
                this.currentModalTitle = 'Adicionar Escola'
                this.currentModalWidth = '500'
                this.currentModal(AddSchoolModal)
            }
            if (modalType == 'edit') {
                this.currentModalTitle = 'Editar Estudante'
                this.currentModal(EditStudentModal)
                this.currentModalWidth = '800'
                this.$router.push({ path: '/students', query: { id: id } })
            }
            if (modalType == 'delete') {
                this.currentModalTitle = 'Deletar Estudante'
                this.currentModal(DeleteStudentModal)
                this.currentModalWidth = '600'
                this.$router.push({ path: '/students', query: { id: id } })
            }
            if (modalType == 'loans') {
                this.currentModalTitle = 'Empréstimos'
                this.currentModal(StudentLoansModal)
                this.currentModalWidth = '900'
                this.$router.push({ path: '/students', query: { id: id } })
            }

            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
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

        async getSearchStudents() {
            this.loadingUsers = true

            await axios.get(`/student/getBySearch?searchText=${this.searchText}`)
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
    },
    template: /*html*/ `
        <div>
            <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Alunos</span></div>
                                
            <v-divider></v-divider>
            
            <div class="d-flex mt-5">
                <v-btn class="ml-16" color="secondary" @click="openModal('addUser')">
                    Adicionar
                    <v-icon right dark>mdi-account-plus</v-icon>
                </v-btn>
                <v-btn class="ml-5" color="secondary" @click="openModal('addSchool')">
                    Adicionar
                    <v-icon right dark>mdi-school</v-icon>
                </v-btn>

                <v-spacer></v-spacer>

                <div class="d-flex mr-16">
                    <v-text-field v-model="searchText" color="teal" placeholder="Pesquisar..." hide-details dense filled clearable></v-text-field>
                    <v-btn @click="getSearchStudents" color="primary"><v-icon color="white">mdi-magnify</v-icon></v-btn>          
                </div>
            </div>
            
            <v-card v-for="student in students" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Nome:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.firstName}} {{student.lastName}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">E-mail:</span>
                            </v-row>
                            <v-row>
                                <span style="word-break: break-all; margin-right: 5px">{{student.email}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{genders.find(it => it.id == student.genderId)?.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Data Nasc.:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.birthDate}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Escola:</span>
                            </v-row>
                            <v-row>
                                <span>{{schools.find(it => it.id == student.schoolId)?.name}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">CPF:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.cpf}}</span>
                            </v-row>
                        </v-col>
                        <v-col v-if="student.loans">
                            <v-row>
                                <v-btn color="primary" @click="openModal('loans', student.id)">
                                    Empréstimos
                                </v-btn>
                            </v-row>
                        </v-col>
                        <div>
                            <v-btn icon @click="openModal('edit', student.id)" class="teal--text d-block">
                                <v-icon>mdi-pencil</v-icon>
                            </v-btn>
                            <v-btn icon @click="openModal('delete', student.id)" class="red--text d-block">
                                <v-icon>mdi-delete</v-icon>
                            </v-btn>
                        </div>
                    </v-row>
                </v-container>
            </v-card>

            <modal-template :title="currentModalTitle" :maxWidth="currentModalWidth">
                <modal v-if="this.currentModalTitle == 'Deletar Estudante' || this.currentModalTitle == 'Adicionar Escola'"/>
                <modal v-if="this.currentModalTitle == 'Adicionar Estudante'" :schools="schools" :genders="genders"/>
                <modal v-if="this.currentModalTitle == 'Editar Estudante'" :schools="schools" :genders="genders"/>
                <modal v-if="this.currentModalTitle == 'Empréstimos'" :loans="currenVAMOStStudent.loans"/>
            </modal-template>
            
        </div>`
}