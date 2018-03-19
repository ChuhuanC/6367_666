package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum ROREQMutator implements MethodMutatorFactory {

    EQ_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new EQMethodVisitor(this, context, methodVisitor);
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

class EQMethodVisitor extends AbstractJumpMutator {

    private static final String                     DESCRIPTION = "negated conditional";
    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<>();

    static {
      
      MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFEQ, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFEQ, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFEQ, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFGT, new Substitution(Opcodes.IFEQ, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFEQ, DESCRIPTION));
      
      MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPEQ,
          DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPEQ,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPEQ,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPGT, new Substitution(Opcodes.IF_ICMPEQ,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPEQ,
              DESCRIPTION));
      
      MUTATIONS.put(Opcodes.IF_ACMPNE, new Substitution(Opcodes.IF_ACMPEQ,
          DESCRIPTION));
    }

   EQMethodVisitor(final MethodMutatorFactory factory,
        final MutationContext context, final MethodVisitor delegateMethodVisitor) {
      super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
      return MUTATIONS;
    }

  }