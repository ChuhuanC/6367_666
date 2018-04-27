package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.MutationIdentifier;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public class UOIMutator {
    public enum RemoveIncrementsMutator implements MethodMutatorFactory {

      UOI_MUTATOR;

      @Override
      public MethodVisitor create(final MutationContext context,
          final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
        return new RemoveIncrementsMethodVisitorC(this, context, methodVisitor);
      }

      @Override
      public String getGloballyUniqueId() {
        return this.getClass().getName();
      }

      @Override
      public String getName() {
        return name();
      }
    }
    
    public enum AddIncrementsMutator implements MethodMutatorFactory {

        UOI_MUTATOR;
        
        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new AddIncrementsMethodVisitorC(this, context, methodVisitor);
        }
        
        @Override
        public String getGloballyUniqueId() {
          return this.getClass().getName();
        }
        
        @Override
        public String getName() {
          return name();
        }
    }
    
    public enum AddDecrementsMutator implements MethodMutatorFactory {

        UOI_MUTATOR;

        @Override
        public MethodVisitor create(final MutationContext context,
            final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
          return new AddDecrementsMethodVisitorC(this, context, methodVisitor);
        }

        @Override
        public String getGloballyUniqueId() {
          return this.getClass().getName();
        }

        @Override
        public String getName() {
          return name();
        }
    }
}

class RemoveIncrementsMethodVisitorC extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  RemoveIncrementsMethodVisitorC(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }

  @Override
  public void visitIincInsn(final int var, final int increment) {
    final MutationIdentifier newId = this.context.registerMutation(
        this.factory, "Removed increment " + increment);
    if (this.context.shouldMutate(newId)) {
      this.mv.visitIincInsn(var, 0);
    } else {
      this.mv.visitIincInsn(var, increment);
    }
  }

}

class AddIncrementsMethodVisitorC extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  AddIncrementsMethodVisitorC(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitVarInsn(final int opcode, final int var) {
    if (opcode == Opcodes.ILOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added integer increment");
      if (this.context.shouldMutate(newId)) {
          this.mv.visitVarInsn(opcode, var);
          this.mv.visitIincInsn(var, 1);
          this.mv.visitVarInsn(Opcodes.ISTORE, var);
          super.visitVarInsn(opcode, var);
      } else {
          super.visitVarInsn(opcode, var);
      }
    } else if (opcode == Opcodes.DLOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added double increment");
      if (this.context.shouldMutate(newId)) {
          this.mv.visitVarInsn(opcode, var);
          this.mv.visitInsn(Opcodes.DUP2);
          this.mv.visitInsn(Opcodes.DCONST_1);
          this.mv.visitInsn(Opcodes.DADD);
          this.mv.visitVarInsn(Opcodes.DSTORE, var);
          super.visitVarInsn(opcode, var);
      } else {
          super.visitVarInsn(opcode, var);
      }
    } else if (opcode == Opcodes.FLOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added float increment");
      if (this.context.shouldMutate(newId)) {
          this.mv.visitVarInsn(opcode, var);
          this.mv.visitInsn(Opcodes.DUP);
          this.mv.visitInsn(Opcodes.FCONST_1);
          this.mv.visitInsn(Opcodes.FADD);
          this.mv.visitVarInsn(Opcodes.FSTORE, var);
          super.visitVarInsn(opcode, var);
      } else {
          super.visitVarInsn(opcode, var);
      }
    } else if (opcode == Opcodes.LLOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added long increment");
      if (this.context.shouldMutate(newId)) {
          this.mv.visitVarInsn(opcode, var);
          this.mv.visitInsn(Opcodes.DUP2);
          this.mv.visitInsn(Opcodes.LCONST_1);
          this.mv.visitInsn(Opcodes.LADD);
          this.mv.visitVarInsn(Opcodes.LSTORE, var);
          super.visitVarInsn(opcode, var);
      } else {
          super.visitVarInsn(opcode, var);
      }
    } else {
      super.visitVarInsn(opcode, var);
    }
  }

}

class AddDecrementsMethodVisitorC extends MethodVisitor {

  private final MethodMutatorFactory factory;
  private final MutationContext      context;

  AddDecrementsMethodVisitorC(final MethodMutatorFactory factory,
      final MutationContext context, final MethodVisitor delegateMethodVisitor) {
    super(Opcodes.ASM6, delegateMethodVisitor);
    this.factory = factory;
    this.context = context;
  }
  
  @Override
  public void visitVarInsn(final int opcode, final int var) {
    if (opcode == Opcodes.ILOAD) {
      final MutationIdentifier newId = this.context.registerMutation(
          this.factory, "Added integer decrement");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitVarInsn(opcode, var);
            this.mv.visitIincInsn(var, -1);
            this.mv.visitVarInsn(Opcodes.ISTORE, var);
            super.visitVarInsn(opcode, var);
        } else {
            super.visitVarInsn(opcode, var);
        }
      } else if (opcode == Opcodes.DLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added double decrement");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitVarInsn(opcode, var);
            this.mv.visitInsn(Opcodes.DUP2);
            this.mv.visitInsn(Opcodes.DCONST_1);
            this.mv.visitInsn(Opcodes.DSUB);
            this.mv.visitVarInsn(Opcodes.DSTORE, var);
            super.visitVarInsn(opcode, var);
        } else {
            super.visitVarInsn(opcode, var);
        }
      } else if (opcode == Opcodes.FLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added float decrement");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitVarInsn(opcode, var);
            this.mv.visitInsn(Opcodes.DUP);
            this.mv.visitInsn(Opcodes.FCONST_1);
            this.mv.visitInsn(Opcodes.FSUB);
            this.mv.visitVarInsn(Opcodes.FSTORE, var);
            super.visitVarInsn(opcode, var);
        } else {
            super.visitVarInsn(opcode, var);
        }
      } else if (opcode == Opcodes.LLOAD) {
        final MutationIdentifier newId = this.context.registerMutation(
            this.factory, "Added long decrement");
        if (this.context.shouldMutate(newId)) {
            this.mv.visitVarInsn(opcode, var);
            this.mv.visitInsn(Opcodes.DUP2);
            this.mv.visitInsn(Opcodes.LCONST_1);
            this.mv.visitInsn(Opcodes.LSUB);
            this.mv.visitVarInsn(Opcodes.LSTORE, var);
            super.visitVarInsn(opcode, var);
        } else {
            super.visitVarInsn(opcode, var);
        }
      } else {
        super.visitVarInsn(opcode, var);
      }
  }

}