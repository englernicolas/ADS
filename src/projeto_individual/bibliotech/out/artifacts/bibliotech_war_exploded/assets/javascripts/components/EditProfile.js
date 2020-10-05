import { $bus } from '../utils/eventBus.js'

import ModalTemplate from './utils/Modal.js'
Vue.component('ModalTemplate', ModalTemplate)

/* MODAIS */
import EditPasswordModal from './modals/EditPasswordModal.js'

export default {
    name: 'EditProfile',
    data: () => ({
        valid: true,
        firstName: 'João',
        lastName: 'Silva',
        birthdayDate: '',
        requiredMessage: [
            v => !!v || 'Campo obrigatório',
        ],
        email: '',
        emailRules: [
            v => /.+@.+\..+/.test(v) || 'Email inválido',
        ],
        cpf: '',
        cpfRules: [
            v => /^\d{3}\.\d{3}\.\d{3}\-\d{2}$/.test(v) || 'CPF inválido',
        ],
        selectedGender: 'Indefinido',
        genders: [
            'Indefinido',
            'Masculino',
            'Feminino',
        ],
        school: 'ESCOLA CABEÇA DE GELO',
        menu: false,
    }),
    mounted() {
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
        }
    },
    template: /*html*/ `<div>
                    <div class="text-center my-5"><span class="grey--text text--darken-3 text-h2 font-weight-bold">Editar Perfil</span></div>
                                        
                    <v-divider></v-divider>
                    
                    <v-form class="mx-16 my-5" ref="form" v-model="valid">
                        <v-container>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="firstName" :rules="requiredMessage" label="Nome" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                                <v-col>
                                    <v-text-field
                                        v-model="lastName" :rules="requiredMessage" label="Sobrenome" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="email" :rules="emailRules" label="E-mail" color="teal" required outlined
                                    ></v-text-field>
                                </v-col>
                                <v-col>    
                                    <v-text-field v-mask="['###.###.###-##']"
                                        v-model="cpf" :rules="requiredMessage && cpfRules" label="CPF" color="teal" outlined required
                                    ></v-text-field>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>    
                                    <v-select
                                        v-model="selectedGender" :items="genders" :rules="[v => !!v || 'Item necessário']" label="Sexo" color="teal" required outlined
                                    ></v-select>
                                </v-col>
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
                                            v-model="birthdayDate"
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
                                        v-model="birthdayDate"
                                        :max="new Date().toISOString().substr(0, 10)"
                                        min="1950-01-01"
                                        @change="save"
                                        color="primary"
                                        ></v-date-picker>
                                    </v-menu>
                                </v-col>
                            </v-row>
                            <v-row>
                                <v-col>
                                    <v-text-field
                                        v-model="school" :rules="requiredMessage" label="Escola" color="teal" disabled outlined
                                    ></v-text-field>
                                </v-col>
                            </v-row>     
                            
                            <v-row>
                                <v-col class="text-right">    
                                    <v-btn color="secondary" class="white--text text-lg-right" @click="openModal">
                                        Alterar Senha
                                    </v-btn>
                                </v-col>
                                <v-col class="text-left">
                                    <v-btn :disabled="!valid" color="primary" class="white--text text-lg-right" @click="validate">
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
