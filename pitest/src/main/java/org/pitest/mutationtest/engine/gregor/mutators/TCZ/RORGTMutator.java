package org.pitest.mutationtest.engine.gregor.mutators.TCZ;

import java.util.HashMap;
import java.util.Map;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.pitest.mutationtest.engine.gregor.AbstractJumpMutator;
import org.pitest.mutationtest.engine.gregor.MethodInfo;
import org.pitest.mutationtest.engine.gregor.MethodMutatorFactory;
import org.pitest.mutationtest.engine.gregor.MutationContext;

public enum RORGTMutator implements MethodMutatorFactory {

    GT_MUTATOR;

    @Override
    public MethodVisitor create(final MutationContext context,
        final MethodInfo methodInfo, final MethodVisitor methodVisitor) {
      return new GTMethodVisitor(this, context, methodVisitor);
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

class GTMethodVisitor extends AbstractJumpMutator {

    private static final String                     DESCRIPTION = "negated conditional";
    private static final Map<Integer, Substitution> MUTATIONS   = new HashMap<>();

    static {
      MUTATIONS.put(Opcodes.IFEQ, new Substitution(Opcodes.IFGT, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFNE, new Substitution(Opcodes.IFGT, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFLE, new Substitution(Opcodes.IFGT, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFGE, new Substitution(Opcodes.IFGT, DESCRIPTION));
      MUTATIONS.put(Opcodes.IFLT, new Substitution(Opcodes.IFGT, DESCRIPTION));
      
      MUTATIONS.put(Opcodes.IF_ICMPNE, new Substitution(Opcodes.IF_ICMPGT,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPEQ, new Substitution(Opcodes.IF_ICMPGT,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPLE, new Substitution(Opcodes.IF_ICMPGT,
          DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPGE, new Substitution(Opcodes.IF_ICMPGT,
              DESCRIPTION));
      MUTATIONS.put(Opcodes.IF_ICMPLT, new Substitution(Opcodes.IF_ICMPGT,
              DESCRIPTION));
    }

    GTMethodVisitor(final MethodMutatorFactory factory,
        final MutationContext context, final MethodVisitor delegateMethodVisitor) {
      super(factory, context, delegateMethodVisitor);
    }

    @Override
    protected Map<Integer, Substitution> getMutations() {
      return MUTATIONS;
    }

  }