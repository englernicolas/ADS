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
        school: 'ESCOLA CABEÇA DE GELO'
    }),
    mounted() {
        this.$options.components.Modal = EditPasswordModal
    },
    methods: {
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
                                    <v-text-field
                                        v-model="school" :rules="requiredMessage" label="Nome" color="teal" disabled outlined
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
