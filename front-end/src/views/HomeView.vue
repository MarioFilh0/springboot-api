<template>
  <div class="p-d-flex p-jc-center p-ai-center" style="min-height: 100vh">
    <div class="card p-shadow-24" style="width: 90%; max-width: 1200px">
      <Toolbar class="p-mb-4">
        <template #start>
          <div v-if="isAdmin">
            <Dropdown
              v-model="selectedDepartment"
              :options="departments"
              placeholder="Selecione o Departamento"
              class="p-mr-2"
            />
            <Button
              label="Aplicar Filtro"
              icon="pi pi-filter"
              @click="loadData"
            />
          </div>
          <div v-else>
            <InputText placeholder="Departamento NGER (Exemplo)" disabled />
          </div>
        </template>
        <template #end>
          <Button
            label="Novo Registro"
            icon="pi pi-plus"
            class="p-button-success"
            @click="openNew"
          />
        </template>
      </Toolbar>

      <DataTable
        :value="userActivities"
        responsiveLayout="scroll"
        :paginator="true"
        :rows="10"
        :rowsPerPageOptions="[5, 10, 25]"
      >
        <Column field="userName" header="Usuário" :sortable="true"></Column>
        <Column header="Educação">
          <template #body="slotProps">
            <p>Profissionalizante: {{ slotProps.data.professionalizante }}</p>
            <p>Formal: {{ slotProps.data.formal }}</p>
            <p>ENEM: {{ slotProps.data.enem }}</p>
            <p>ENCSEJA: {{ slotProps.data.encceja }}</p>
            <p>Outras Atividades: {{ slotProps.data.otherEducation }}</p>
          </template>
        </Column>
        <Column header="Trabalho">
          <template #body="slotProps">
            <p>Marcenaria: {{ slotProps.data.marcenaria }}</p>
            <p>Oficina de Costura: {{ slotProps.data.oficinaCostura }}</p>
            <p>
              Limpeza e Conservação: {{ slotProps.data.limpezaConservacao }}
            </p>
            <p>Campanhas: {{ slotProps.data.campanhas }}</p>
          </template>
        </Column>
        <Column :exportable="false" style="min-width: 8rem">
          <template #body="slotProps">
            <Button
              icon="pi pi-pencil"
              class="p-button-rounded p-button-success p-mr-2"
              @click="editActivity(slotProps.data)"
            />
            <Button
              icon="pi pi-trash"
              class="p-button-rounded p-button-warning"
              @click="confirmDeleteActivity(slotProps.data)"
            />
          </template>
        </Column>
      </DataTable>

      <Dialog
        v-model:visible="activityDialog"
        :style="{ width: '450px' }"
        header="Detalhes da Atividade"
        :modal="true"
        class="p-fluid"
      >
        <div class="field">
          <label for="userName">Usuário</label>
          <InputText
            id="userName"
            v-model.trim="activity.userName"
            required="true"
            autofocus
            :class="{ 'p-invalid': submitted && !activity.userName }"
          />
          <small class="p-error" v-if="submitted && !activity.userName"
            >Nome do usuário é obrigatório.</small
          >
        </div>

        <h5>Educação</h5>
        <div class="p-grid p-dir-col">
          <div class="p-col">
            <label for="professionalizante">Profissionalizante:</label>
            <InputNumber
              id="professionalizante"
              v-model="activity.professionalizante"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="formal">Formal:</label>
            <InputNumber
              id="formal"
              v-model="activity.formal"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="enem">ENEM:</label>
            <InputNumber
              id="enem"
              v-model="activity.enem"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="encceja">ENCSEJA:</label>
            <InputNumber
              id="encceja"
              v-model="activity.encceja"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="otherEducation">Outras Atividades Educacionais:</label>
            <InputNumber
              id="otherEducation"
              v-model="activity.otherEducation"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
        </div>

        <h5 class="p-mt-3">Trabalho</h5>
        <div class="p-grid p-dir-col">
          <div class="p-col">
            <label for="marcenaria">Marcenaria:</label>
            <InputNumber
              id="marcenaria"
              v-model="activity.marcenaria"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="oficinaCostura">Oficina de Costura:</label>
            <InputNumber
              id="oficinaCostura"
              v-model="activity.oficinaCostura"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="fabricaBlocos">Fábrica de Blocos:</label>
            <InputNumber
              id="fabricaBlocos"
              v-model="activity.fabricaBlocos"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="artesanato">Artesanato:</label>
            <InputNumber
              id="artesanato"
              v-model="activity.artesanato"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="oficinaMecanica">Oficina Mecânica:</label>
            <InputNumber
              id="oficinaMecanica"
              v-model="activity.oficinaMecanica"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="construcaoCivil">Construção Civil:</label>
            <InputNumber
              id="construcaoCivil"
              v-model="activity.construcaoCivil"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="culinaria">Culinária:</label>
            <InputNumber
              id="culinaria"
              v-model="activity.culinaria"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="padaria">Padaria:</label>
            <InputNumber
              id="padaria"
              v-model="activity.padaria"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="corteCostura">Corte e Costura:</label>
            <InputNumber
              id="corteCostura"
              v-model="activity.corteCostura"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="esteticista">Esteticista:</label>
            <InputNumber
              id="esteticista"
              v-model="activity.esteticista"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="fabricaFraldas">Fábrica de Fraldas:</label>
            <InputNumber
              id="fabricaFraldas"
              v-model="activity.fabricaFraldas"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="fabricaAbsorventes">Fábrica de Absorventes:</label>
            <InputNumber
              id="fabricaAbsorventes"
              v-model="activity.fabricaAbsorventes"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="manutencaoArCondicionado"
              >Manutenção de Ar-Condicionado:</label
            >
            <InputNumber
              id="manutencaoArCondicionado"
              v-model="activity.manutencaoArCondicionado"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="eletricista">Eletricista:</label>
            <InputNumber
              id="eletricista"
              v-model="activity.eletricista"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="limpezaConservacao">Limpeza e Conservação:</label>
            <InputNumber
              id="limpezaConservacao"
              v-model="activity.limpezaConservacao"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
          <div class="p-col">
            <label for="campanhas">Campanhas:</label>
            <InputNumber
              id="campanhas"
              v-model="activity.campanhas"
              showButtons
              mode="decimal"
              :min="0"
            />
          </div>
        </div>

        <template #footer>
          <Button
            label="Cancelar"
            icon="pi pi-times"
            class="p-button-text"
            @click="hideDialog"
          />
          <Button
            label="Salvar"
            icon="pi pi-check"
            class="p-button-text"
            @click="saveActivity"
          />
        </template>
      </Dialog>

      <ConfirmDialog></ConfirmDialog>
      <Toast />
    </div>
  </div>
</template>

<script>
import { ref, onMounted } from "vue";
import axios from "axios"; // Para fazer requisições HTTP
import { useToast } from "primevue/usetoast"; // Para notificações

// Componentes PrimeVue que vamos usar
import DataTable from "primevue/datatable";
import Column from "primevue/column";
import Toolbar from "primevue/toolbar";
import Button from "primevue/button";
import Dialog from "primevue/dialog";
import InputText from "primevue/inputtext";
import InputNumber from "primevue/inputnumber";
import Dropdown from "primevue/dropdown";
import ConfirmDialog from "primevue/confirmdialog";
import Toast from "primevue/toast";

export default {
  components: {
    DataTable,
    Column,
    Toolbar,
    Button,
    Dialog,
    InputText,
    InputNumber,
    Dropdown,
    ConfirmDialog,
    Toast,
  },
  setup() {
    const toast = useToast();

    // Dados da tabela e do formulário
    const userActivities = ref([]);
    const activityDialog = ref(false);
    const activity = ref({});
    const submitted = ref(false);
    const isAdmin = ref(true); // Mude para false para testar como usuário comum
    const departments = ref(["NGER", "GAB", "OUTROS"]); // Departamentos de exemplo
    const selectedDepartment = ref(null);

    // Endpoint do backend
    const API_URL = "http://localhost:8080/api/user-activities"; // Ajuste a porta se seu backend estiver em outra

    // Simulação de usuário logado (deve vir do seu sistema de autenticação)
    // Para fins de demonstração, vamos simular que o ADMIN tem uma flag
    const checkUserRole = () => {
      // Em um sistema real, isso viria da sua API de autenticação
      // Por exemplo, após o login, você receberia um token JWT com as roles do usuário
      // localStorage.getItem('userRole') ou de um store Vuex
      // if (localStorage.getItem('userRole') === 'ADMIN') {
      //     isAdmin.value = true;
      // } else {
      //     isAdmin.value = false;
      // }
      // Por enquanto, isAdmin está fixo para demonstração
    };

    // Carregar dados da API
    const loadData = async () => {
      try {
        let url = API_URL;
        if (isAdmin.value && selectedDepartment.value) {
          url += `?department=${selectedDepartment.value}`; // Exemplo de filtro
        } else if (!isAdmin.value) {
          // Em um cenário real, você enviaria o ID do usuário logado ou o backend filtraria pela sessão
          // url += `?userId=${loggedInUserId.value}`;
          // Por enquanto, para usuários comuns, não filtra por departamento visivelmente
        }

        const response = await axios.get(url);
        userActivities.value = response.data;
      } catch (error) {
        console.error("Erro ao carregar dados:", error);
        toast.add({
          severity: "error",
          summary: "Erro",
          detail: "Falha ao carregar dados",
          life: 3000,
        });
      }
    };

    // Funções CRUD
    const openNew = () => {
      activity.value = {};
      submitted.value = false;
      activityDialog.value = true;
    };

    const hideDialog = () => {
      activityDialog.value = false;
      submitted.value = false;
    };

    const saveActivity = async () => {
      submitted.value = true;
      if (activity.value.userName) {
        // Verificação básica
        try {
          if (activity.value.id) {
            // Atualizar
            await axios.put(`${API_URL}/${activity.value.id}`, activity.value);
            toast.add({
              severity: "success",
              summary: "Sucesso",
              detail: "Atividade atualizada",
              life: 3000,
            });
          } else {
            // Criar novo
            await axios.post(API_URL, activity.value);
            toast.add({
              severity: "success",
              summary: "Sucesso",
              detail: "Atividade criada",
              life: 3000,
            });
          }
          activityDialog.value = false;
          activity.value = {};
          loadData(); // Recarregar dados após salvar
        } catch (error) {
          console.error("Erro ao salvar atividade:", error);
          toast.add({
            severity: "error",
            summary: "Erro",
            detail: "Falha ao salvar atividade",
            life: 3000,
          });
        }
      }
    };

    const editActivity = (prod) => {
      activity.value = { ...prod }; // Copia os dados para edição
      activityDialog.value = true;
    };

    const confirmDeleteActivity = (prod) => {
      toast.add({
        severity: "warn",
        summary: "Confirmação",
        detail: "Você tem certeza que deseja excluir este registro?",
        group: "confirm",
        life: 5000,
        data: prod, // Passa o produto para a função onConfirm
      });
    };

    const deleteActivity = async (prod) => {
      try {
        await axios.delete(`${API_URL}/${prod.id}`);
        toast.add({
          severity: "success",
          summary: "Sucesso",
          detail: "Atividade excluída",
          life: 3000,
        });
        loadData(); // Recarregar dados após exclusão
      } catch (error) {
        console.error("Erro ao excluir atividade:", error);
        toast.add({
          severity: "error",
          summary: "Erro",
          detail: "Falha ao excluir atividade",
          life: 3000,
        });
      }
    };

    // Montar os componentes
    onMounted(() => {
      checkUserRole(); // Verifica a role do usuário ao iniciar
      loadData(); // Carrega os dados iniciais
    });

    return {
      userActivities,
      activityDialog,
      activity,
      submitted,
      isAdmin,
      departments,
      selectedDepartment,
      openNew,
      hideDialog,
      saveActivity,
      editActivity,
      confirmDeleteActivity,
      deleteActivity,
      loadData,
      toast,
    };
  },
};
</script>

<style scoped>
/* Estilos específicos para este componente */
.card {
  background: var(--surface-card);
  padding: 2rem;
  border-radius: 10px;
  margin: 2rem auto; /* Centraliza o card */
}

.p-fluid .p-col {
  margin-bottom: 1rem; /* Espaçamento entre campos no formulário */
}

/* Para centralizar na tela */
.p-d-flex {
  display: flex;
}
.p-jc-center {
  justify-content: center;
}
.p-ai-center {
  align-items: center;
}
.p-mr-2 {
  margin-right: 0.5rem;
}
.p-mb-4 {
  margin-bottom: 2rem;
}
.p-mt-3 {
  margin-top: 1.5rem;
}
.p-grid.p-dir-col .p-col {
  width: 100%; /* Garante que os campos de quantidade fiquem em uma coluna */
}
</style>
