import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

import SearchBox from './utils/SearchBox.js'
Vue.component('SearchBox', SearchBox)

/* MODAIS */
import AddStudentModal from './modals/AddStudentModal.js'
import AddSchoolModal from './modals/AddSchoolModal.js'
import EditStudentModal from './modals/EditStudentModal.js'
import DeleteStudentModal from './modals/DeleteStudentModal.js'
import StudentLoansModal from './modals/StudentLoansModal.js'

export default {
    name: 'Students',
    data: () => ({
        students: [
            {
                id: 1,
                firstName: "Nicolas",
                lastName: "Engler",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                school: "Senai",
                cpf: '12313213213',
                loans: [
                    {
                        id: 1,
                        title: "O casamento",
                        author: "Nicholas Sparks",
                        gender: "Romance",
                        loanDate: "15/03/2002",
                        deliveryDate: "15/04/2002",
                        debt: '12',
                    },
                    {
                        id: 2,
                        title: "O casamento",
                        author: "Nicholas Sparks",
                        gender: "Romance",
                        loanDate: "15/03/2002",
                        deliveryDate: "15/04/2002",
                    },
                ],
            },
            {
                id: 2,
                firstName: "Amigão",
                lastName: "Engler",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                school: "Senai",
                cpf: '12313213213',
                loans: [
                    {
                        id: 1,
                        title: "O cdsadsaasamento",
                        author: "Nichoasdlas Sparks",
                        gender: "Romance",
                        loanDate: "15/03/2002",
                        deliveryDate: "15/04/2002",
                        debt: '12',
                    },
                    {
                        id: 2,
                        title: "O cadsadsasamento",
                        author: "Nichodsadsalas Sparks",
                        gender: "Romance",
                        loanDate: "15/03/2002",
                        deliveryDate: "15/04/2002",
                    },
                ],
            },
            {
                id: 3,
                firstName: "Opa",
                lastName: "Engler",
                email: "nicolas@gmail.com",
                gender: "Masculino",
                birthDate: "15/03/2002",
                school: "Senai",
                cpf: '12313213213',
                loans: false
            },
        ],
        currentModalTitle: '',
        currentModalWidth: '',
        currentStudent: '',
    }),
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

            if (id) {
                this.currentStudent = this.students.find(it => it.id == id)
            }
            
            $bus.$emit('open-modal')
        },
        currentModal(modal) {
            this.$options.components.Modal = modal
        }
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

                <search-box class="mr-16"></search-box>
            </div>

            <v-card v-for="student in students" class="mx-16 my-5">
                <v-container>
                    <v-row class="mx-3">
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Nome:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.firstName}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">E-mail:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.email}}</span>
                            </v-row>
                        </v-col>
                        <v-col>
                            <v-row>
                                <span class="font-weight-bold">Gênero:</span>
                            </v-row>
                            <v-row>
                                <span>{{student.gender}}</span>
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
                                <span>{{student.school}}</span>
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
                <modal/>
                <modal v-if="this.currentModalTitle == 'Editar Estudante'" :student="currentStudent"/>
                <modal v-if="this.currentModalTitle == 'Empréstimos'" :loans="currentStudent.loans"/>
            </modal-template>
            
        </div>`
}